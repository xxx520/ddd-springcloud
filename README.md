# :palm_tree: Domain driven design 微服务(企业级)注册中心主架构

***
##  :four_leaf_clover: Eureka and Zuul Component </br>
+ 企业级微服务架构相对于互联网的（eureka->gateway->monitor->config->springboot）设计上可以封闭，或者方便一些。开发人员安排上也不会过多去招人来开发。本人开发过上述的互联网微服务架构，相对于来说本项目的架构较为轻便也可以说是方便。</br>
+ 本项目Eureka负责提供注册，Zuul作为定期拉取注册中心存活的微服务，亦在Zuul网关里设置限流和负载异常捕获。
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
