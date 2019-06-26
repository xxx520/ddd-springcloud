package org.yugh.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * //
 *
 * @author 余根海
 * @creation 2019-04-10 18:09
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@EnableFeignClients
@SpringCloudApplication
@EnableEurekaClient
public class Application {
    //FIXME 基层提供操作库服务

    //FIXME 把auth client 的一些基础拆到此项目中 - by yugenhai
    //FIXME 通过 jersey + swagger 对外提供API

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
