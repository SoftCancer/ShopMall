package com.dongl.shopbasicseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ShopBasicsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBasicsEurekaApplication.class, args);
    }

}
