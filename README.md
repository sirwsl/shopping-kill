## 商品秒杀系统（已上线）
### 商城地址：https://kill.wslhome.top
### 后台管理：https://admin.wslhome.top

**项目V1.0版本已上线，该项目是今年毕设项目。** 
PS：码云同步仓库地址：https://gitee.com/sirwsl/ShoppingKill

两处代码完全一致，gitee上的gitlog记录更加详细
**喜欢就给个♥** 

作者：sirwsl

博客：https://www.wslhome.top

CSDN: https://blog.csdn.net/qq_40432886

欢迎交流：sirwsl@163.com

WX：sirwsl


### 使用说明
#### 1、前端部署说明
前端文件在resources资源目录下的html文件中，运行时候把整个html文件夹直接copy或者cut出来

注：项目在打包时候是去除html文件的，只包含后端文件

前端部署Tomcat或者Nginx，直接把整个html文件夹复制到www或者对应目录下，然后配置tomcat或者nginx进行启动

nginx配置参见项目中的nginx.conf

注意：前端端口设置8081，如果更改前端端口，请更改对应的com.wsl.shoppingkill.config.request下的跨域设置

访问：必须使用localhost，访问地址如下
```
http://localhost:8081/html/user/index.html   #商城主页
http://localhost:8081/html/admin/login.html  #后台管理
```

#### 2、后端部署说明
后端必须运行80端口，否则需要更改前端文件的请求路径比较麻烦

可以用idea直接运行，也可以打成jar包 Java -jar运行

你也可以使用docker运行，参见dockerfile



### 知识点
* springboot+Mybatis-plus搭建
* redis、zookeeper、mq集群
* redis缓存、jetCache缓存
* RabbitMq消息通知、死信、延迟队列使用
* zookeeper分布式锁
* 自定义注解
* 统一封装返回
* 切面使用
* 设计模式使用
* 事物、回滚使用
* docker、nginx使用
* 图片服务器OSS使用
* stram、lambda使用
* 多线程、线程池使用
* 定时任务使用
* 短信验证、邮件服务使用
* JWT验证TOKEN令牌使用
* 雪花算法分布式算法使用
* 拦截器、Log4j日志使用
* 还有很多的工具类、组件使用
* 数据库连接池Druid使用
* MapStruct实体转化工具使用
* 然后部署时候CDN之类的使用
* ... ...

### 效果
PS：不过多展示图片，需要看效果自己访问kill.wslhome.top 或 admin.wslhome.top
![前端首页](https://github.com/sirwsl/shopping-kill/raw/master/view/QQ20210112-182222.png)
![商城](https://github.com/sirwsl/shopping-kill/raw/master/view/QQ20210112-182254.png)
![后台主页](https://github.com/sirwsl/shopping-kill/raw/master/view/QQ20210112-182437.png)
![后台活动](https://github.com/sirwsl/shopping-kill/raw/master/view/QQ20210112-182453.png)
![后台主页](https://github.com/sirwsl/shopping-kill/raw/master/view/QQ20210112-182453.png)
![我的订单](https://github.com/sirwsl/shopping-kill/raw/master/view/QQ20210112-182349.png)

### 简介

项目主要针对秒杀系统进行实现，前后端分离项目，其中设计到技术主要为SpringBoot、Zookeeper、Redis、rabbitMQ。
添加一键部署，mybatis-plus逆向生成 其中融入短信通知（榛子云）、邮件通知（boot-starter-mail）、短信验证（kaptcha）、一二级缓存（JetCache）、跨域登录（jjwt）、数据库连接（druid），还包括自定义注解、切面编程等相关东西

### 一、项目概况
采用SpringBoot+中间件实现在高并发业务场景下商品的的限时抢购秒杀系统，本题目基于线上电商平台，以高可靠、高负载、高并发来实现商品的限时抢购系统。


### 二、目录说明
```
.
├── Dockerfile   docker配置文件
├── README.md
├── doc
│   ├── ER
│   ├── api  接口文档
├── shopping-kill.iml
├── sql
│   ├── shoppingkill-num.sql
│   └── shoppingkill.sql  项目sql
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── wsl
│   │   │           └── shoppingkill
│   │   │               ├── Application.java  启动类
│   │   │               ├── common   公共一些工具 日志切面  异常封装
│   │   │               ├── component   一些自定义组件
│   │   │               ├── config   配置类
│   │   │               ├── controller  C层
│   │   │               ├── domain   实体
│   │   │               ├── mapper  mapper层
│   │   │               ├── obj     VO｜BO｜Param｜DTO
│   │   │               ├── service  实现接口层
│   │   │               └── serviceImpl 实现层
│   │   └── resources
│   │       ├── application.yml  配置文件
│   │       ├── config  配置文件
│   │       ├── ftl    邮件模版-未优化
│   │       ├── log4j.properties 日志配置
│   │       ├── mapper  sql的xml 文件
│   │       └── templates
│   │           ├── admin  商城后台
│   │           ├── static  静态资源
│   │           └── user  商城前端
│   └── test  测试文件
|
└── target  生产
 
```

### 三、完成进度

参见doc下的完成进度表  

### 四、主要技术

(一)、整体架构：

1、Redis主从架构：

本系统为保证系统的高可用、高负载、和容灾性，通过redis集群主从复制实现，在集群环境中master会自动将数据同步到slave，可以进行读写分离，从而实现分载master的读操作压。master是以非阻塞的方式为slave提供服务，通过redis主从架构进一步实现负载均衡与故障恢复，从而提高系统的容灾性。

2、Zookeeper集群架构：

通过Zookeeper集群架构实现了应用的简单性，可靠性、可拓展性、透明性。Zookeeper基本更进一步提高了系统的整体容灾性能。

3、RabbitMQ集群架构：

通过RabbitMQ集群化部署实现系统的高可靠高可用，对于限时抢购系统，rabiitMq更加提供了高可用、高并发的成熟方案。

4、OSS存储：

在系统的整体架构中，OSS存储将单个对象分离为meta、data两部分，并将二者分离存储，OSS实现了Region级资源快速调度能力，在用户有突发带宽、Qps需求而旧数据所在集群无法满足时，可以按前缀、或者按比例将一个桶的新写入数据在一个Region内不同集群，甚至不同的AZ见调度从而快速满足用户需求。通过镜像会员实现Region级容灾。从而在满足与资源存储的基础上提供限时抢购系统的性能。

5、CDN加速：

由于CDN几乎覆盖国内的所有线路，对于可靠性来说，不会因为限时抢购系统Qps锅盖，意外而导致网站的内容无法访问，由于是全国化多节点覆盖，在意外节点发生故障后，对网站的访问会自动导向其他健康节点，从而实现整体的稳定性。通过CDN的使用，极大减轻服务器压力，提高网站的访问速度。

6、网站集群化部署：

通过网站的集群化部署，能够更好的实现负载均衡，提高系统的并发量。



(二)、实现框架：SpringBoot+MySQL+MybatisPlus+BootStrap


1、RabbitMQ：通过rabbitmq的使用，在系统中进行短信推送、邮件推送、订单管理等模块中更好的实现了系统的解耦，以异步方式进行操作，使得系统更加平稳运行。通过消息队列的应用，在高并发场景下进行限流，从而保证系统的可运行，起到削峰作用。

2、Zookeeper：

通过zookeeper分布式锁的应用，保证数据在并发环境下的数据一致性与安全性，通过实现读写所，从而更好的提高系统的性能。

3、Redis：

系统中通过运用redis缓存用户基本信息除外，通过将商品进行提前加入缓存，进一步提升读写性能，通过redis缓存的运用提高用户访问量，同时引入JetCache采用两级缓存redis+ caffeine，从而防止缓存雪崩、缓存击穿的场景出现。

(四)、其余技术：

1、SnowFlake

雪花算法，系统中订单生成以及相关的id采用雪花算法替代传统的UUID进行实现，通过41bit的时间戳、10bit的工作机器id与12bit的序列号实现64bit的大整数，实现每秒20万id生成，从而在并发环境中提高系统性能。

2、Druid

在系统数据库连接采用druid代替传统的jdbc连接方式，通过连接池的方式实现，更好的实现数据的增删查改。通过druid可视化界面，更好的对sql性能进行整体监控。

3、Nginx

通过运行nginx代替创痛的Tomcat服务器，在网站集群化部署的过程中实现负载均衡，在高并发的环境下，网络稳定性对其影响非常笑，通过nginx负载均衡，更好的是的网站能够承担高在压力且稳定。

4、Docker

在docker中更好的为系统提供了持续部署与测试。docker可以通过确保从开发到产品发布整个过程环境的一致性来解决这个问题Docker容器通过相关配置，保持容器内部所有的配置和依赖关系始终不变。其在安全性上docker内部容器在部署时实现应用程序将的完全分割与隔离，更好的保证系统的安全性。





