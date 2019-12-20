package com.mmc.price;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-07 10:25
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.mmc.price.mapper")
public class ProductPriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductPriceApplication.class,args);
    }
}
