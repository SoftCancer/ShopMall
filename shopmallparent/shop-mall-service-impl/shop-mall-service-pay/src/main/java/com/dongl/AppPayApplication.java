package com.dongl;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 聚合支付模块依赖于 shop-mall-pay-plugin
 * @author: YaoGuangXun
 * @date: 2020/2/18 16:00
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2Doc
@MapperScan(basePackages = "com.dongl.pay.mapper")
public class AppPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppPayApplication.class,args);
    }
}
