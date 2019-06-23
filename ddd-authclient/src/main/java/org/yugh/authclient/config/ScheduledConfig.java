package org.yugh.authclient.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Nonnull;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * //调度线程池配置
 *
 * @author: 余根海
 * @creation: 2019-06-20 14:36
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    /**
     * 手动创建线程池大小
     * 准备3个空闲线程数
     */
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3,
            new BasicThreadFactory.Builder().namingPattern("springSecurityAuth-schedule-pool-%d").daemon(true).build());


    @Override
    public void configureTasks(@Nonnull ScheduledTaskRegistrar scheduledTaskRegistrar){
        try{
            scheduledTaskRegistrar.setScheduler(setTaskExecutors());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置线程池
     * @return
     */
    @Bean(destroyMethod = "")
    public Executor setTaskExecutors(){
        return executorService;
    }
}
