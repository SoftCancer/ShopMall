package com.dongl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 19:29
 * @Version: 1.0
 */


@EnableAsync
@EnableHystrix  // 用于服务降级，防止服务雪崩
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.dongl.spike.service.mapper")
public class AppSpikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppSpikeApplication.class,args);
    }
}

