package com.mmc.product.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-09 22:32
 **/
@Component
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queue,String message){
        amqpTemplate.convertAndSend(queue,message);
    }

}
