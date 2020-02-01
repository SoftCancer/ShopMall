package com.dongl.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:32
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMemberApplication.class,args);
    }
}
