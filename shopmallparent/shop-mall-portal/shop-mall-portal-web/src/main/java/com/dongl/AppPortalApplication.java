package com.dongl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 门户启动类
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:34
 * @Version: 1.0
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })  // 作用：去除引入其他模块的jar时，附带的数据库引用。
public class AppPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppPortalApplication.class,args);
    }
}
