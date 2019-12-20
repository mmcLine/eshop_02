package com.mmc.dataaggr.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmc.common.constant.MQqueueConstant;
import com.mmc.common.rabbitmq.MQDimTypeData;
import com.mmc.common.rabbitmq.MQEventData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-12 21:31
 **/
@Component
@Slf4j
public class AggrDataChangeReceiver {

    @Autowired
    private JedisPool jedisPool;

    @RabbitHandler
    @RabbitListener(queues = MQqueueConstant.AGGR_DATA_CHANGE_QUEUE)
    public void process(String message) {
        log.info("接收到消息："+message);
        MQDimTypeData mqEventData = JSON.parseObject(message,MQDimTypeData.class);
        switch (mqEventData.getDimType()){
            case "brand":
            case "category":
            case "product_intro":
                processDimdataChange(mqEventData);
                break;
            case "product":
            case "product_property":
            case "product_specification":
                processProductDimdataChange(mqEventData);
                break;
        }
    }

    private void processDimdataChange(MQDimTypeData dimTypeData){
        Integer id = dimTypeData.getId();
        String dimType = dimTypeData.getDimType();
        Jedis jedis = jedisPool.getResource();
            String dataJson = jedis.get(dimType+"_" + id);
            if(StringUtils.isNotEmpty(dataJson)){
                jedis.set("dim_"+dimType+"_"+id,dataJson);
            }else {
                jedis.del("dim_"+dimType+"_"+id);
            }


    }

    private void processProductDimdataChange(MQDimTypeData dimTypeData){
        Integer id = dimTypeData.getId();
        Jedis jedis = jedisPool.getResource();
        List<String> redisResultList = jedis.mget("product_" + id, "product_property_" + id, "product_specification_" + id);
        String productStr = redisResultList.get(0);
        if(StringUtils.isNotEmpty(productStr)){
            JSONObject productJson = JSON.parseObject(productStr);
                String productPropertyStr = redisResultList.get(1);
            if(StringUtils.isNotEmpty(productPropertyStr)){
                productJson.put("product_property",JSON.parseObject(productPropertyStr));
            }

            String product_specificationStr = redisResultList.get(2);
            if(StringUtils.isNotEmpty(product_specificationStr)){
                productJson.put("product_specification",JSON.parseObject(product_specificationStr));
            }

            jedis.set("dim_product_"+id,productJson.toJSONString());
        }else {
            jedis.del("dim_product_"+id);
        }
    }
}
