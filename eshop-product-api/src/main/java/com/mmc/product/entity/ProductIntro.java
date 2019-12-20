package com.mmc.product.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 19:50
 **/
@Data
public class ProductIntro {
    @Id
    private Integer id;
    private String content;
    private Integer productId;
}
