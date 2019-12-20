package com.mmc.common.rabbitmq;

import lombok.Data;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-10 22:00
 **/
@Data
public class MQDimTypeData extends MQData{
    private Integer id;
    private String dimType;

    public MQDimTypeData(Integer id, String dimType) {
        this.id = id;
        this.dimType = dimType;
    }

    public MQDimTypeData() {
    }
}
