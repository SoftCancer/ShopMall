package com.dongl.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/24 12:45
 * @Version: 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AppJenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppJenkinsApplication.class,args);
    }
}
