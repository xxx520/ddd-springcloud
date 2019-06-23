package org.yugh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class DddZuulApplication {

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 允许任何域名使用
		corsConfiguration.addAllowedOrigin("*");
		// 允许任何头
		corsConfiguration.addAllowedHeader("*");
		// 允许任何方法（post、get等）
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 对接口配置跨域设置
		source.registerCorsConfiguration("/appH5/**", buildConfig());
		return new CorsFilter(source);
	}
	public static void main(String[] args) {
		SpringApplication.run(DddZuulApplication.class, args);
	}
}
