package com.mmc.product.rest;

import com.alibaba.fastjson.JSON;
import com.mmc.common.rest.BaseController;
import com.mmc.product.biz.ProductPropertyBiz;
import com.mmc.product.entity.ProductProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 20:52
 **/
@RestController
@RequestMapping("productProperty")
public class ProductPropertyController extends BaseController<ProductPropertyBiz,ProductProperty> {



    @RequestMapping("/findByProductId/{productId}")
    public String findByProductId(@PathVariable(value = "productId") Integer productId){
        Example example=new Example(ProductProperty.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        List<ProductProperty> productProperties = this.baseBiz.selectByExample(example);
        if (productProperties.size()>0){
            return JSON.toJSONString(productProperties);
        }else return "";

    }

}
