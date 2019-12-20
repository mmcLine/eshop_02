package com.mmc.datalink.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmc.datalink.feign.IEshopProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-15 14:14
 **/
@RestController
@RefreshScope
public class DataLinkController {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private IEshopProductService eshopProductService;

    @RequestMapping("/getProduct")
    @HystrixCommand(fallbackMethod = "getProductFallback")
    public String getProduct(Integer productId){
        Jedis jedis=jedisPool.getResource();
        String dimProductJSON = jedis.get("dim_product_" + productId);
        jedis.close();
        if(StringUtils.isEmpty(dimProductJSON)){
            String product = eshopProductService.findProductById(productId);
            JSONObject productJson = JSON.parseObject(product);
            if(productJson!=null&&StringUtils.isNotEmpty(productJson.getString("data"))){
                String productProperty = eshopProductService.findProductPropertyByProductId(productId);
                if(StringUtils.isNotEmpty(productProperty)){
                    productJson.put("product_property",JSON.parseArray(productProperty));
                }

                String product_specificationStr = eshopProductService.findProductSpecificationByProductId(productId);
                if(StringUtils.isNotEmpty(product_specificationStr)){
                    productJson.put("product_specification",JSON.parseArray(product_specificationStr));
                }
            }

            jedis.set("dim_product_"+productId,productJson.toJSONString());
            return productJson.toJSONString();
        }else {
            return dimProductJSON;
        }

    }

    public String getProductFallback(Integer productId){
        return "网络出错，请稍后重试！！！";
    }


    @Value("${defaultName}")
    private String defaultName;

    @RequestMapping("/hi")

    public String hi(String name){
        if(org.apache.commons.lang.StringUtils.isEmpty(name)){
            name=defaultName;
        }
        return name;
    }
}
