package com.mmc.product.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @description: 商品属性
 * @author: mmc
 * @create: 2019-12-08 19:51
 **/
@Data
public class ProductProperty {
    @Id
    private Integer id;
    private String name;
    private String value;
    private Integer productId;

}
