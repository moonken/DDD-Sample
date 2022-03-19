package com.example.autopartsmall.common.infrastructure.eventbus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskScheduler = new ThreadPoolTaskExecutor();
        taskScheduler.setCorePoolSize(5);
        taskScheduler.setMaxPoolSize(10);
        taskScheduler.setKeepAliveSeconds(1000);
        taskScheduler.setThreadNamePrefix("taskExecutorSub-");
        taskScheduler.setAwaitTerminationSeconds(10 * 60);
        taskScheduler.setQueueCapacity(20);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskScheduler.initialize();
        return taskScheduler;
    }

}
