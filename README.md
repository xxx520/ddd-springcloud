# :palm_tree: Domain driven design 微服务(企业级)注册中心主架构

***
##  :four_leaf_clover: Eureka and Zuul Component </br>
+ 企业级并发量不高。Eureka负责提供注册，Zuul作为定期拉取注册中心存活的微服务，亦在Zuul网关里设置限流和负载异常捕获。
## :hibiscus: AuthClient and Springboot </br>
+ AuthClient 作为一个Web做鉴权操作（Spring Security），拦截非login、auth等请求。
+ 下层Springboot鉴权微服务提供正常读写库操作（如鉴权），提供方式用swagger.codegen对Controller功能生成ApiClient。
+ AuthClient 拿到下层鉴权的ApiClient做功能请求接入或者返回。
***

## :ear_of_rice: service = Springboot各个微服务组件
***
## *简单架构示意图*
![Image text](https://github.com/yugenhai108/ddd-springcloud/blob/master/springcloud-ddd.jpg)
