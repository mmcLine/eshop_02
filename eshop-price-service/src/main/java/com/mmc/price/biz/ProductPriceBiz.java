package com.mmc.price.biz;

import com.alibaba.fastjson.JSON;
import com.mmc.common.biz.BaseBiz;
import com.mmc.price.entity.ProductPrice;
import com.mmc.price.mapper.ProductPriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 22:32
 **/
@Service
public class ProductPriceBiz extends BaseBiz<ProductPriceMapper,ProductPrice> {

    @Autowired
    private JedisPool jedisPool;


    @Override
    public void insertSelective(ProductPrice entity) {
        super.insertSelective(entity);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_price_"+entity.getProductId(), JSON.toJSONString(entity));
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
        Jedis jedis = jedisPool.getResource();
        ProductPrice productPrice = selectById(id);
        jedis.del("product_price_"+productPrice.getProductId());
    }

    @Override
    public void updateSelectiveById(ProductPrice entity) {
        super.updateSelectiveById(entity);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_price_"+entity.getProductId(), JSON.toJSONString(entity));
    }
}
