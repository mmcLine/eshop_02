package com.mmc.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-08 23:06
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.mmc.inventory.mapper")
public class ProductInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductInventoryApplication.class,args);
    }
}
