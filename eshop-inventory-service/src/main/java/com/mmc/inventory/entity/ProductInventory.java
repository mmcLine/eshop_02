package com.mmc.inventory.entity;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @description: 库存
 * @author: mmc
 * @create: 2019-12-08 22:25
 **/
@Data
public class ProductInventory {
    @Id
    private Integer id;
    private Integer num;
    private Integer productId;
}
