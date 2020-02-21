package com.dongl.zuul;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
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
//@EnableApolloConfig  // 开启阿波罗
@SpringBootApplication
@MapperScan(basePackages = "com.dongl.zuul.mapper")
public class AppZuulApplication {
    
    /**
     *  第二版 手动获取通过 阿波罗 的 Config 获取Json
     * @Date: 2020/2/4 15:46
     *
        @ApolloConfig
        private Config config;
     */

    /**
     *  第三版 通过value注解 取 阿波罗的Json
     * @Date: 2020/2/4 15:46
     **/
    @Value("${dongl.zuul.swagger.document}")
    private String swaggerDocument;


    public static void main(String[] args) {
        SpringApplication.run(AppZuulApplication.class, args);
    }

    /**
     * 通过 Zuul 实现 Swagger Api 的路由转发，运行时 需要开启 微信服务和会员服务
     *
     * @Author: YaoGuangXun
     * @Date: 2020/2/1 19:04
     **/
    @Component
    @Primary
    public class DocumentationConfig implements SwaggerResourcesProvider {

        @Override
        public List<SwaggerResource> get() {

            /* 第一版 需要配合yml 配置文件
            List resources = new ArrayList();
            resources.add(swaggerResource("app-member","/app-member/v2/api-docs","2.0.0"));
            resources.add(swaggerResource("app-weixin","/app-weixin/v2/api-docs","2.0.0"));
            */
            /* 第二版 在阿波罗 配置 第一版信息*/
            return getResource();
        }
    }

    /**
     * 遍历从apollo 获取的每个模块的Swagger ；
     *
     * @Date: 2020/2/4 2:50
     **/
    private List<SwaggerResource> getResource() {
        List<SwaggerResource> list = new ArrayList();
//        String swaggerDocument = swaggerDocument();
        System.out.println(swaggerDocument);
        JSONArray jsonArray = JSONArray.parseArray(swaggerDocument);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String name = jsonObject.getString("name");
            String location = jsonObject.getString("location");
            String version = jsonObject.getString("version");
            list.add(swaggerResource(name, location, version));
        }

        return list;
    }


    /**
     * @Description: 从 阿波罗 获取配置文件
     * @Author: YaoGuangXun
     * @Date: 2020/2/4 2:38
     *
    private String swaggerDocument() {
        String property = config.getProperty("dongl.zuul.swaggerDocument", "");
        return property;
    }
     */

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
