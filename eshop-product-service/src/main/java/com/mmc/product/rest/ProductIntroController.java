package com.mmc.product.rest;

import com.mmc.common.rest.BaseController;
import com.mmc.product.biz.ProductIntroBiz;
import com.mmc.product.entity.ProductIntro;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 20:51
 **/
@RestController
@RequestMapping("productIntro")
public class ProductIntroController extends BaseController<ProductIntroBiz,ProductIntro> {
}
