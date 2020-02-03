package com.dongl.member;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
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
@EnableSwagger2Doc
@EnableApolloConfig  // 通过apollo需要 同时启动 AppWeiXinApplication 和该应用
public class AppMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMemberApplication.class,args);
    }
}
