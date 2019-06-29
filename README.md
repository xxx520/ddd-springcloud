# :palm_tree: Domain driven design 微服务(企业级)注册中心主架构

***
##  :four_leaf_clover: Eureka and Zuul Component </br>
+ 1：相对于互联网的（eureka->gateway->monitor->config->springboot）设计方式。
在领域驱动架构设计上可以对每个建模的对象进行Events事件发起到触发或结束的监听，比如一个场景，建模了user，在某一个事件发起时，可以创建一个UserEvents，registerEvent到当前user里，那么在其他地方操作某一个事件并带了该用户，那么久可以拿到该建模用户里的注册事件的操作记录。
+ 2：企业级并发量不高。Eureka负责提供注册，Zuul作为定期拉取注册中心存活的微服务，亦在Zuul网关里设置限流和负载异常捕获。
## :hibiscus: AuthClient and Springboot </br>
+ 1：AuthClient 作为一个Web做鉴权操作（Spring Security），拦截非login、auth等请求。
+ 2：下层Springboot鉴权微服务提供正常读写库操作（如鉴权），提供方式用swagger.codegen对Controller功能生成ApiClient。
+ 3：AuthClient 拿到下层鉴权的ApiClient做功能请求接入或者返回。

***
## :seedling: 领域驱动建模规范：
+ 路由(Route)->控制器(Controller)->接口(Api)->服务(Service)->仓库(Repository)->工厂(Factory)->实体(Entity)
***
## :ear_of_rice: service = Springboot各个微服务组件
## :four_leaf_clover: ~~目前是简易swagger页面路径，需要慢慢完善（比如完整从基层到鉴权端输出调用方式 || 加swagger带Token注解）：~~

+ eureka url :http://localhost:8010/
+ auth client url :http://localhost:8030/swagger-ui.html
***
## :whale: *简单架构示意图*
![Image text](https://github.com/yugenhai108/ddd-springcloud/blob/master/springcloud-ddd.jpg)
