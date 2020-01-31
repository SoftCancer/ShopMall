# ShopMall
一个电商项目

项目说明
该想项目是采用目前比较流行的SpringBoot/SpringCloud构建微服务电商项目，实现一套串联的微服务电商项目，能完全掌握该知识，可以在一线城市拿到月薪25+k薪资。
完全符合一线城市微服务电商的需求，对中国程序猿学习微服务电商架构，有非常大的帮助，该项目涵盖从微服务电商需求讨论、数据库设计、技术选型、互联网安全架构、整合SpringCloud各自组件、分布式基础设施、使Docker+k8s+jenkins实现微服务自动化部署、项目上线域名配置、SEO优化、大数据统计分析等。

技术选型
A. 项目采用SpringBoot2.x+SpringCloud2.x构建微服务电商项目
1.使用SpringCloudEureka作为注册中心，实现服务治理
2.使用Zuul网关框架统一管理微服务请求入口
3.使用Ribbon实现本地负载均衡器和FeginHttp客户端调用工具
4.使用Hystrix服务保护框架(服务降级、隔离、熔断、限流)
5.使用消息总线Stream RabbitMQ和Kafka
6.微服务API接口安全控制与单点登陆系统CAS+JWT+Oauth2.0

B. 分布式基础设施环境构建
1.分布式任务调度平台XXL-Job
2.分布式日志采集系统ELK +kafka
3.分布式事务解决方案Rabitmq
4.分布式锁解决方案Zookeeper、Redis
5.分布式配置中心携程阿波罗
6.高并发分布式全局ID生成雪花算法
7.分布式Session框架Spring-Session
8.分布式服务追踪与调用链ZipKin

C.项目运营与部署环境
1.分布式设施环境，统一采用docker安装
2.使用jenkins+docker+k8s实现自动部署
3.微服务API管理ApiSwagger
4.使用GitLab代码管理
5.统一采用第三方云数据库
6.使用七牛云服务器对静态资源实现加速
7.构建企业级Maven私服

系统架构
