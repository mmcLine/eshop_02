package com.mmc.datasync.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmc.common.constant.MQqueueConstant;
import com.mmc.common.msg.ObjectRestResponse;
import com.mmc.common.rabbitmq.MQData;
import com.mmc.common.rabbitmq.MQDimTypeData;
import com.mmc.common.rabbitmq.MQEventData;
import com.mmc.common.utils.StringHelper;
import com.mmc.datasync.feign.IEshopProductService;
import com.mmc.product.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.mmc.common.constant.MQqueueConstant.DATA_CHANGE_QUEUE;


/**
 * @description:
 * @author: mmc
 * @create: 2019-12-10 21:41
 **/
@Component
@Slf4j
public class DataChangeQueueReceiver {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private IEshopProductService productService;

    public DataChangeQueueReceiver(){
        new SendMessageThread().start();
    }

    /**
     * 做消息去重队列
     */
    private Set<String> messageSet=Collections.synchronizedSet(new HashSet<>());

    private List<Integer> brandIdList=new CopyOnWriteArrayList<>();

    @RabbitHandler
    @RabbitListener(queues = DATA_CHANGE_QUEUE)
    public void process(String message) {
        log.info("接收到消息："+message);
        MQEventData mqEventData = JSON.parseObject(message,MQEventData.class);
        processDataChangeMessage(mqEventData);
    }

    private void processDataChangeMessage(MQEventData mqEventData) {
        Integer id = mqEventData.getId();
        if(mqEventData.getProductId()!=null){
            id=mqEventData.getProductId();
        }
        switch (mqEventData.getDataType()){
            case "brand":
                brandIdList.add( mqEventData.getId());
                if(brandIdList.size()>=3){
                    List datalist = (List) productService.findBrandByIds(brandIdList).getData();
                    for (Object brand:datalist){
                        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(brand));
                        processChangeMessage(mqEventData,jsonObject.getInteger("id"),"brand",jsonObject.toJSONString());
                    }
                    brandIdList.clear();
                }
                break;
            case "category":
                String categoryData = JSON.parseObject(productService.findCategoryById( mqEventData.getId())).getString("data");
                processChangeMessage(mqEventData,id,"category",categoryData);
                break;
            case "product":
                String productData = JSON.parseObject(productService.findProductById( mqEventData.getId())).getString("data");
                processChangeMessage(mqEventData,id,"product",productData);
                break;
            case "product_intro":
                String productIntroData = JSON.parseObject(productService.findProductIntroById( mqEventData.getId())).getString("data");
                processChangeMessage(mqEventData,id,"product_intro",productIntroData);
                break;
            case "product_property":
                String productPropertyData = JSON.parseObject(productService.findProductPropertyById( mqEventData.getId())).getString("data");
                processChangeMessage(mqEventData,id,"product",productPropertyData);
                break;
            case "product_specification":
                String productSpecificationData = JSON.parseObject(productService.findProductSpecificationById( mqEventData.getId())).getString("data");
                processChangeMessage(mqEventData,id,"product",productSpecificationData);
                break;

        }

    }

    private void processChangeMessage(MQEventData mqEventData,Integer id,String dimype,String data){
        Jedis jedis=null;
       try {
           String eventType = mqEventData.getEventType();
           jedis= jedisPool.getResource();
           if(MQData.ADD.equals(eventType) || MQData.UPDATE.equals(eventType)) {
               JSONObject dataJSONObject = JSONObject.parseObject(data);
               jedis.set(mqEventData.getDataType()+"_" + id, dataJSONObject.toJSONString());

           } else if (MQData.DELETE.equals(eventType)) {
               jedis.del(mqEventData.getDataType()+"_" + id);
           }
           MQDimTypeData dimTypeData=new MQDimTypeData(id,dimype);
           messageSet.add(JSON.toJSONString(dimTypeData));
       }catch (Exception e){
           e.printStackTrace();
       }finally {
          if(jedis!=null){
              jedis.close();
          }
       }
    }


    private class SendMessageThread extends Thread{
        @Override
        public void run() {
            while (true){
                for (String message:messageSet){
                    if(StringUtils.isNotEmpty(message)){
                        rabbitMQSender.send(MQqueueConstant.AGGR_DATA_CHANGE_QUEUE, message);
                    }
                }
                messageSet.clear();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

 }
