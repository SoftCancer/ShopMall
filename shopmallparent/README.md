基于SpringCloud 的分布式电商项目

#项目说明
该想项目是采用目前比较流行的SpringBoot/SpringCloud构建微服务电商项目，实现一套串联的微服务电商项目，能完全掌握该知识，可以在一线城市拿到月薪25+k薪资。
完全符合一线城市微服务电商的需求，对中国程序猿学习微服务电商架构，有非常大的帮助，该项目涵盖从微服务电商需求讨论、数据库设计、技术选型、互联网安全架构、整合SpringCloud各自组件、分布式基础设施、使Docker+k8s+jenkins实现微服务自动化部署、项目上线域名配置、SEO优化、大数据统计分析等。

#技术选型
##A. 项目采用SpringBoot2.x+SpringCloud2.x构建微服务电商项目

1.使用SpringCloudEureka作为注册中心，实现服务治理

2.使用Zuul网关框架统一管理微服务请求入口

3.使用Ribbon实现本地负载均衡器和FeginHttp客户端调用工具

4.使用Hystrix服务保护框架(服务降级、隔离、熔断、限流)

5.使用消息总线Stream RabbitMQ和Kafka

6.微服务API接口安全控制与单点登陆系统CAS+JWT+Oauth2.0

##B. 分布式基础设施环境构建

1.分布式任务调度平台XXL-Job

2.分布式日志采集系统ELK +kafka

3.分布式事务解决方案Rabitmq

4.分布式锁解决方案Zookeeper、Redis

5.分布式配置中心携程阿波罗

6.高并发分布式全局ID生成雪花算法

7.分布式Session框架Spring-Session

8.分布式服务追踪与调用链ZipKin

##C.项目运营与部署环境

1.分布式设施环境，统一采用docker安装

2.使用jenkins+docker+k8s实现自动部署

3.微服务API管理ApiSwagger

4.使用GitLab代码管理

5.统一采用第三方云数据库

6.使用七牛云服务器对静态资源实现加速

7.构建企业级Maven私服

#系统架构

#项目构建
##A. 分布式基础设施项目
--shop-mall-parent-----公共Pranet依赖

---shop-mall-basics----分布式基础设施

-----shop-mall-basics-springcloud-eureka—注册中心

-----shop-mall-basics-apollo-config-server—阿波罗分布式配置中心

-----shop-mall-basics-springcloud-zuul—统一请求入口

-----shop-mall-basics-xuxueli-xxljob—分布式任务调度平台

-----shop-mall-basics-codingapi-lcn—分布式事务解决框架

-----shop-mall-basics-codingapi-zipKin —分布式调用链系统

-----shop-mall-basics-codingapi-elk —分布式日志收集


##B. 公共服务接口层
---shop-mall-service-api提供公共接口

-----shop-mall-service-api-weixin 微信服务接口

-----shop-mall-service-api-member会员服务接口

-----shop-mall-service-api-sso sso服务接口

-----shop-mall-service-api-item商品服务接口

-----shop-mall-service-api-search 搜索服务接口

-----shop-mall-service-api-pay聚合支付平台

-----shop-mall-service-api-order订单服务接口

-----shop-mall-service-api-spike 秒杀服务接口

##C. 公共服务接口实现层
---shop-mall-service-impl公共接口的实现

-----shop-mall-service-weixin 微信服务接口实现

-----shop-mall-service-member会员服务接口实现

-----shop-mall-service-api-sso sso服务接口实现

-----shop-mall-service-tem商品服务接口实现

-----shop-mall-service-search 搜索服务接口实现

-----shop-mall-service-pay聚合支付平台接口实现

-----shop-mall-service-order订单服务接口实现

-----shop-mall-service-api-spike 秒杀服务接口

##D. 公共服务接口实体类
---shop-mall-api-dto 开放接口实体类层

-----shop-mall-service-api-weixin-dto 微信实体类

-----shop-mall-service-api-member-dto 会员实体类

-----shop-mall-service-api-sso-dto sso实体类

-----shop-mall-service-api-item-dto 商品实体类

-----shop-mall-service-api-search-dto 搜索实体类

-----shop-mall-service-api-pay-dto 聚合支付实体类

-----shop-mall-service-api-order-dto 订单实体类

-----shop-mall-service-api-spike-dto秒杀实体类

##E. 门户平台
---shop-mall-portal 门户平台

-----shop-mall-portal-web 门户网站

-----shop-mall-portal-sso 单点登陆系统

-----shop-mall-portal-search 搜索系统

-----shop-mall-portal-spike 秒杀系统

-----shop-mall-portal-cms 运营系统

##F. 工具类
---shop-mall-common 工具类
-----shop-mall-common-core—核心工具类
-----shop-mall-common-security-安全

##G. 分布式任务调度实现
---shop-mall-service-job 分布式定时任务调度

-----shop-mall-service-weixin-job 微信定时任务

-----shop-mall-service-member-job 会员定时任务

-----shop-mall-service-tem-job 商品定时任务

-----shop-mall-service-search-job 搜索定时任务

-----shop-mall-service-order-job 订单定时任务

-----shop-mall-service-api-spike-job 秒杀定时任务

#环境要求
为了能够更好的学习互联网微服务架构，该项目对环境要求非常高，建议电脑配置CPU在I5、32GB内存或者电脑采用集群化部署。

1.JDK统一要求:JDK1.8K

2.Maven 统一管理Jar

3.统一采用Docker安装软件

4.编码统一采用为UTF-8

5.开发工具IDE或者Eclipse


## 参考：https://github.com/qq644064779/shop-mall