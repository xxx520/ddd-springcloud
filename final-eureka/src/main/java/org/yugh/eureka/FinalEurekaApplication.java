package org.yugh.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FinalEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalEurekaApplication.class, args);
	}
}
