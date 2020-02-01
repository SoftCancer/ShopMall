package com.dongl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 14:26
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class AppWeiXinApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppWeiXinApplication.class,args);
    }
}
