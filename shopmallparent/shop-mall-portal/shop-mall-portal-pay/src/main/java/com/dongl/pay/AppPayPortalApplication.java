package com.dongl.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *  同时启动
 * @author: YaoGuangXun
 * @date: 2020/2/11 22:04
 * @Version: 1.0
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })  // 作用：去除引入其他模块的jar时，附带的数据库引用。

public class AppPayPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppPayPortalApplication.class,args);
    }
}
