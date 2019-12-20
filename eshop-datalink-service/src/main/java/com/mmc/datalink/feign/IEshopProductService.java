package com.mmc.datalink.feign;

import com.mmc.common.msg.ObjectRestResponse;
import com.mmc.datalink.fallback.IEshopProductServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eshop-product",fallback = IEshopProductServiceFallBack.class)
public interface IEshopProductService {

    @RequestMapping(value = "/brand/findById/{id}",method = RequestMethod.GET)
    String findBrandById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/brand/findByIds",method = RequestMethod.POST)
    ObjectRestResponse findBrandByIds(@RequestBody List<Integer> ids);

    @RequestMapping(value = "/category/findById/{id}",method = RequestMethod.GET)
    String findCategoryById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/productIntro/findById/{id}",method = RequestMethod.GET)
    String findProductIntroById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/productProperty/findById/{id}",method = RequestMethod.GET)
    String findProductPropertyById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/productProperty/findByProductId/{productId}",method = RequestMethod.GET)
    String findProductPropertyByProductId(@PathVariable(value = "productId") Integer productId);

    @RequestMapping(value = "/product/findById/{id}",method = RequestMethod.GET)
    String findProductById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/productSpecification/findById/{id}",method = RequestMethod.GET)
    String findProductSpecificationById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/productSpecification/findByProductId/{productId}",method = RequestMethod.GET)
    String findProductSpecificationByProductId(@PathVariable(value = "productId") Integer productId);

}
