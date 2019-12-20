package com.mmc.product.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @description: 品牌
 * @author: mmc
 * @create: 2019-12-06 23:52
 **/
@Data
public class Brand {
    @Id
    private Integer id;
    private String name;
    private String description;

}
