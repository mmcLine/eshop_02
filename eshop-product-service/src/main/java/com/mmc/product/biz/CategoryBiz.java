package com.mmc.product.biz;

import com.alibaba.fastjson.JSON;
import com.mmc.common.biz.BaseBiz;
import com.mmc.common.constant.MQqueueConstant;
import com.mmc.common.rabbitmq.MQEventData;
import com.mmc.product.entity.Category;
import com.mmc.product.mapper.CategoryMapper;
import com.mmc.product.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-07 09:58
 **/
@Service
public class CategoryBiz extends BaseBiz<CategoryMapper,Category> {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void insertSelective(Category entity) {
        super.insertSelective(entity);
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.ADD,"category");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
        MQEventData eventData=new MQEventData(Integer.valueOf(id.toString()),MQEventData.DELETE,"category");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
    }

    @Override
    public void updateSelectiveById(Category entity) {
        MQEventData eventData=new MQEventData(entity.getId(),MQEventData.UPDATE,"category");
        rabbitMQSender.send(MQqueueConstant.DATA_CHANGE_QUEUE,JSON.toJSONString(eventData));
        super.updateSelectiveById(entity);
    }
}
