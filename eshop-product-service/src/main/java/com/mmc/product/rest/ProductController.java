package com.mmc.product.rest;

import com.mmc.common.rest.BaseController;
import com.mmc.product.biz.ProductBiz;
import com.mmc.product.entity.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-07 09:56
 **/
@RestController
@RequestMapping("product")
public class ProductController extends BaseController<ProductBiz,Product> {
}
