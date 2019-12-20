package com.mmc.product.rest;

import com.mmc.common.rest.BaseController;
import com.mmc.product.biz.CategoryBiz;
import com.mmc.product.entity.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-07 10:00
 **/
@RestController
@RequestMapping("category")
public class CategoryController extends BaseController<CategoryBiz,Category> {
}
