package org.yugh.authclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * //多个数据源管理
 *
 * @author: 余根海
 * @creation: 2019-04-09 17:36
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "userDataSource")
    @Qualifier("userDataSource")
    @ConfigurationProperties(prefix="spring.datasource.user")
    @Primary
    public DataSource getUserDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "goodsDataSource")
    @Qualifier("goodsDataSource")
    @ConfigurationProperties(prefix="spring.datasource.goods")
    public DataSource getGoodsDataSource() {
        return DataSourceBuilder.create().build();
    }
}
