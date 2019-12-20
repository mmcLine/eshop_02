package com.mmc.product.rest;

import com.alibaba.fastjson.JSON;
import com.mmc.common.rest.BaseController;
import com.mmc.product.biz.ProductSpecificationBiz;
import com.mmc.product.entity.ProductProperty;
import com.mmc.product.entity.ProductSpecification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 20:53
 **/
@RestController
@RequestMapping("productSpecification")
public class ProductSpecificationController extends BaseController<ProductSpecificationBiz,ProductSpecification> {

    @RequestMapping("/findByProductId/{productId}")
    public String findByProductId(@PathVariable(value = "productId") Integer productId){
        Example example=new Example(ProductSpecification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        List<ProductSpecification> productProperties = this.baseBiz.selectByExample(example);
        if (productProperties.size()>0){
            return JSON.toJSONString(productProperties);
        }else return "";
    }


}
