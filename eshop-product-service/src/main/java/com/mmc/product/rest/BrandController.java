package com.mmc.product.rest;

import com.mmc.common.rest.BaseController;
import com.mmc.product.biz.BrandBiz;
import com.mmc.product.entity.Brand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-07 09:59
 **/
@RestController
@RequestMapping("brand")
public class BrandController extends BaseController<BrandBiz,Brand> {
}
