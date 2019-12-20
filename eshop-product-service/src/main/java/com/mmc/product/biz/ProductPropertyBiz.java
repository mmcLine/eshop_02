package com.mmc.product.biz;

import com.alibaba.fastjson.JSON;
import com.mmc.common.biz.BaseBiz;
import com.mmc.common.constant.MQqueueConstant;
import com.mmc.common.rabbitmq.MQEventData;
import com.mmc.product.entity.ProductProperty;
import com.mmc.product.mapper.ProductPropertyMapper;
import com.mmc.product.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 20:49
 **/
@Service
public class ProductPropertyBiz extends BaseBiz<ProductPropertyMapper,ProductProperty> {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void insertSelective(ProductProperty entity) {
        super.insertSelective(entity);
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.ADD,"product_property",entity.getProductId());
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
        ProductProperty productProperty = selectById(id);
        MQEventData eventData=new MQEventData(Integer.valueOf(id.toString()),MQEventData.DELETE,"product_property",productProperty.getProductId());
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void updateSelectiveById(ProductProperty entity) {
        super.updateSelectiveById(entity);
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.UPDATE,"product_property",entity.getProductId());
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }
}
