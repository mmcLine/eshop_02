package com.mmc.inventory.rest;

import com.mmc.common.rest.BaseController;
import com.mmc.inventory.biz.ProductInventoryBiz;
import com.mmc.inventory.entity.ProductInventory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 22:33
 **/
@RestController
@RequestMapping("productinventory")
public class ProductInventoryController extends BaseController<ProductInventoryBiz,ProductInventory> {
}
