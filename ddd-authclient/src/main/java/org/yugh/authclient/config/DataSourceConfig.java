package org.yugh.authclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * //多个数据源管理
 *
 * 避免一个项目使用多个数据源，用多个微服务进行解耦
 *
 * @author: 余根海
 * @creation: 2019-04-09 17:36
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
//@Configuration
public class DataSourceConfig {

    @Bean(name = "getUserDataSource")
    @Qualifier("getUserDataSource")
    @ConfigurationProperties(prefix="spring.datasource.user")
    public DataSource getUserDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "getGoodsDataSource")
    @Qualifier("getGoodsDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.goods")
    public DataSource getGoodsDataSource() {
        return DataSourceBuilder.create().build();
    }
}
