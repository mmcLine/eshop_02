package com.mmc.product.biz;

import com.alibaba.fastjson.JSON;
import com.mmc.common.biz.BaseBiz;
import com.mmc.common.constant.MQqueueConstant;
import com.mmc.common.rabbitmq.MQEventData;
import com.mmc.product.entity.Product;
import com.mmc.product.mapper.ProductMapper;
import com.mmc.product.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-06 23:56
 **/
@Service
public class ProductBiz extends BaseBiz<ProductMapper,Product> {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void insertSelective(Product entity) {
        super.insertSelective(entity);
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.ADD,"product");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
        MQEventData eventData=new MQEventData(Integer.valueOf(id.toString()),MQEventData.DELETE,"product");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void updateSelectiveById(Product entity) {
        super.updateSelectiveById(entity);
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.UPDATE,"product");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }
}
