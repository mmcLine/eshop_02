package com.mmc.product.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-06 23:50
 **/

@Data
public class Product {
    @Id
    private Integer id;
    private String name;
    private Integer categoryId;   //分类id
    private Integer brandId;       //品牌id
}
