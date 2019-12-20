package com.mmc.common.rabbitmq;

import lombok.Data;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-09 23:18
 **/
@Data
public class MQEventData extends MQData{



    private Integer id;
    private String eventType;    //事件类型  add,update,delete
    private String dataType;     //数据类型
    private Integer productId;

    public MQEventData(){}

    public MQEventData(Integer id, String eventType, String dataType) {
        this.id = id;
        this.eventType = eventType;
        this.dataType = dataType;
    }

    public MQEventData(Integer id, String eventType, String dataType, Integer productId) {
        this.id = id;
        this.eventType = eventType;
        this.dataType = dataType;
        this.productId = productId;
    }
}
