package com.dongl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/14 15:30
 * @Version: 1.0
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"com.dongl.product.es"})
public class AppGoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGoodApplication.class,args);
    }
}
