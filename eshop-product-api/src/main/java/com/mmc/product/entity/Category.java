package com.mmc.product.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @description: 分类
 * @author: mmc
 * @create: 2019-12-06 23:51
 **/
@Data
public class Category {
    @Id
    private Integer id;
    private String name;
    private String description;

}
