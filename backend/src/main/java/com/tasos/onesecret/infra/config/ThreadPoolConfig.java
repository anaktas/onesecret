package com.tasos.onesecret.infra.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {
    @Bean("mainThreadPoolExecutor")
    public ThreadPoolTaskExecutor mainThreadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean(name = "mainExecutor", destroyMethod = "shutdown")
    public ScheduledExecutorService mainExecutor() {
        return Executors.newScheduledThreadPool(4);
    }
}
