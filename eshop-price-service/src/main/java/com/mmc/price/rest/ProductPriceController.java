package com.mmc.price.rest;

import com.mmc.common.rest.BaseController;
import com.mmc.price.biz.ProductPriceBiz;
import com.mmc.price.entity.ProductPrice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 22:33
 **/
@RestController
@RequestMapping("productprice")
public class ProductPriceController extends BaseController<ProductPriceBiz,ProductPrice> {
}
