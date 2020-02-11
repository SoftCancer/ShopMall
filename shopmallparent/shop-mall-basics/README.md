
# 模块介绍：

### 一、模块 shop-basics-eureka 主要是 eureka 服务。


### 二、模块 shop-basics-zuul 主要是 网关 zuul 服务。

### 三、模块 shop-basics-xxl-sso-core :是 XXL-SSO 单点登录核心模块，主要服务于 shop-basics-xxl-sso-server 模块，可以打成 jar到私服，然后引用。
        1. 添加 Redis 密码，项目默认无密码，需要在 core.util.JedisUtil.java 类中新增redis 的密码。

### 四、模块 shop-basics-xxl-sso-server 主要实现 XXL-SSO 单点登录服务。
        1.引入该模块后需要修改 WebController.java 类，在其里面新增登录认证功能。
        
        