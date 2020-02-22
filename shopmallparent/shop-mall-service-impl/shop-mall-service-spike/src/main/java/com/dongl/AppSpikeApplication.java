package com.dongl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 19:29
 * @Version: 1.0
 */
@MapperScan("com.dongl.spike.service.mapper")
@EnableEurekaClient
@SpringBootApplication
public class AppSpikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppSpikeApplication.class,args);
    }
}
