package com.mmc.datalink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-09 22:41
 **/
@Configuration
public class JedisConfig {


    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000*10);
        config.setTestOnBorrow(true);
        return new JedisPool(config,"localhost",6379);
    }
}
