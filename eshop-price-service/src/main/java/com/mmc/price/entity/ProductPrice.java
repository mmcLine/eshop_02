package com.mmc.price.entity;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 22:25
 **/
@Data
public class ProductPrice {
    @Id
    private Integer id;
    private BigDecimal price;
    private Integer productId;
}
