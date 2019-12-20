package com.mmc.inventory.biz;

import com.alibaba.fastjson.JSON;
import com.mmc.common.biz.BaseBiz;
import com.mmc.inventory.entity.ProductInventory;
import com.mmc.inventory.mapper.ProductInventoryMapper;
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
public class ProductInventoryBiz extends BaseBiz<ProductInventoryMapper,ProductInventory> {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void insertSelective(ProductInventory entity) {
        super.insertSelective(entity);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_inventory_"+entity.getProductId(), JSON.toJSONString(entity));
    }

    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
        Jedis jedis = jedisPool.getResource();
        ProductInventory productInventory = selectById(id);
        jedis.del("product_inventory_"+productInventory.getProductId());
    }

    @Override
    public void updateSelectiveById(ProductInventory entity) {
        super.updateSelectiveById(entity);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_inventory_"+entity.getProductId(), JSON.toJSONString(entity));
    }
}
