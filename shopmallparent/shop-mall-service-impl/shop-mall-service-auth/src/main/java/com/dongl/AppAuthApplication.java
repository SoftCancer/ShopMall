package com.dongl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  验证认证，第三方访问需要启动：shop-basics-eureka ，shop-basics-zuul，
 *  shop-mall-service-pay ，shop-mall-service-auth 四个模块。
 * 访问地址：
 *  http://127.0.0.1/public/api-pay/cratePayToken?payAmount=3000&orderId=3234567&userId=12343&accessToken=auth_66f3266b4cc640bab6d84be0185f0a04
 *  其中 ：/public/api-pay/  是通过网关进行映射。
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = "com.dongl.auth.mapper")
public class AppAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppAuthApplication.class, args);
	}

}
