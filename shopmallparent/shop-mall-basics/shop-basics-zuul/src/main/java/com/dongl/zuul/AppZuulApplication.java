package com.dongl.zuul;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 微服务网关入口
 * @author: YaoGuangXun
 * @date: 2020/2/1 18:35
 * @Version: 1.0
 */
@EnableSwagger2Doc   // 开启Swagger功能
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class AppZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppZuulApplication.class,args);
    }


    /**
     *  通过 Zuul 实现 Swagger Api 的路由转发，运行时 需要开启 微信服务和会员服务
     * @Author: YaoGuangXun
     * @Date: 2020/2/1 19:04
     **/
    @Component
    @Primary
    public class DocumentationConfig implements SwaggerResourcesProvider{

        @Override
        public List<SwaggerResource> get() {

            List resources = new ArrayList();
            resources.add(swaggerResource("app-member","/app-member/v2/api-docs","2.0.0"));
            resources.add(swaggerResource("app-weixin","/app-weixin/v2/api-docs","2.0.0"));
            return resources;
        }
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
