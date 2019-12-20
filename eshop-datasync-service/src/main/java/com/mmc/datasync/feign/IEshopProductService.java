package com.mmc.datasync.feign;

import com.mmc.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("eshop-product")
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

    @RequestMapping(value = "/product/findById/{id}",method = RequestMethod.GET)
    String findProductById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/productSpecification/findById/{id}",method = RequestMethod.GET)
    String findProductSpecificationById(@PathVariable(value = "id") Integer id);
}
