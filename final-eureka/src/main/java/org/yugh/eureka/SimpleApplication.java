package org.yugh.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * //dev
 *
 * @author: 余根海
 * @creation: 2019-06-17 20:14
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@EnableDiscoveryClient
public class SimpleApplication {

    public static void main(String[] args) {

        SpringApplication.run(SimpleApplication.class, args);
    }

}
