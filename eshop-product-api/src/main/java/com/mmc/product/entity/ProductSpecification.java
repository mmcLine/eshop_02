package com.mmc.product.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @description:规格
 * @author: mmc
 * @create: 2019-12-08 19:51
 **/
@Data
public class ProductSpecification {
    @Id
    private Integer id;
    private String name;
    private String value;
    private Integer productId;

}
