package org.yugh.authclient.config;

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

  /**  @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource goodsDataSource() {
        return DataSourceBuilder.create().build();
    } **/
}
