package com.mmc.product.biz;

import com.alibaba.fastjson.JSON;
import com.mmc.common.biz.BaseBiz;
import com.mmc.common.constant.MQqueueConstant;
import com.mmc.common.rabbitmq.MQEventData;
import com.mmc.product.entity.Brand;
import com.mmc.product.mapper.BrandMapper;
import com.mmc.product.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-07 09:59
 **/
@Service
public class BrandBiz extends BaseBiz<BrandMapper,Brand> {
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void insertSelective(Brand entity) {
        super.insertSelective(entity);
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.ADD,"brand");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void deleteById(Object id) {
        MQEventData eventData=new MQEventData(Integer.valueOf(id.toString()),MQEventData.DELETE,"brand");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
        super.deleteById(id);
    }


    @Override
    public void updateSelectiveById(Brand entity) {
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.UPDATE,"brand");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
        super.updateSelectiveById(entity);
    }
}
