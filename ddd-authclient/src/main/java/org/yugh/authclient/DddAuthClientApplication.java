package org.yugh.authclient;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * //dev
 * DDD = domain driven design
 * 领域驱动 企业级注册中心和路由架构
 * 架构设计：Eureka负责提供注册心跳
 * Zuul路由定期去拉Eureka存活的已注册的微服务
 * 类似GateWay是用一层WEB端（Spring Security）做，无需加GateWay组件
 * Spring Security = Ddd-AuthClient
 * @author: 余根海
 * @creation: 2019-06-17 20:14
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@EnableScheduling
@EnableFeignClients
@SpringCloudApplication
public class DddAuthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddAuthClientApplication.class, args);
	}
}
