
## 该模块集成了 elasticsearch 注意点

### 1. 在配置文件中 application.yml 中
#### 数据库的配置需要注意：
  1. 数据库的名称为：索引名称
    url: jdbc:mysql://47.94.10.48:3306/goods
    
  2. cluster-name ：为集群的名字。
  
  3. cluster-nodes ： 节点的端口号为：9300 。
  
     
    #####数据库相关连接
    datasource:
      username: root
      password: 123456
      driver-class-name: com.mysql.cj.jdbc.Driver
      ###  数据库的名称为
      url: jdbc:mysql://47.94.10.48:3306/goods?useUnicode=true&characterEncoding=UTF-8
    data:
      elasticsearch:
      ####集群名称 和配置文件中的 cluster_name 保持一致
      cluster-name: elasticsearch-cluster
      ####   9200端口是用来让HTTP REST API来访问ElasticSearch，而9300端口是传输层监听的默认端口。
      cluster-nodes: 192.168.1.107:9300


## 搜索时 ，无法进行中文搜索问题。
1.在安装 elasticsearch 时，已安装中文分词器，项目中却无法查询中文。（若没有安装中文分词器，则先安装分词器）

    解决：
    1.先删除 索引goods
        DELETE /goods
    2. 再添加索引 goods
        PUT /goods
