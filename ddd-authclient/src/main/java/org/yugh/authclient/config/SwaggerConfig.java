package org.yugh.authclient.config;

import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * //swagger简易API访问路径
 *
 * @author: 余根海
 * @creation: 2019-04-09 18:17
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String deployType ;
    @Value(("${server.port}"))
    private String serverPort;

    private Contact contact = new Contact("ddd-auth-client", "localhost:${serverPort}/swagger-ui.html", "yugenhai108@gmail.com");

    @Bean
    public Docket createRestApi() {
        log.info("\n =============> swagger config ............ deployType = {}, serverPort = {}", deployType, serverPort);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("auth-client接口API")
                .description("auth-client说明文档")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
