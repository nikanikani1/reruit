package com.nika.recruit.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    /**
     * 异步任务线程池
     * @return
     */
    @Bean
    public ExecutorService commonAsyncThreadPool() {
        return new ThreadPoolExecutor(10, 20,
                1L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(20),
                new ThreadFactoryBuilder().setNameFormat("async-call-%s").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}