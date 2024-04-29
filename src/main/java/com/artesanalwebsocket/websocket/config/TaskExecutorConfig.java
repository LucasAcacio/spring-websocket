package com.artesanalwebsocket.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.*;

@Configuration
public class TaskExecutorConfig {

    @Primary
    @Bean(name="taskExecutor")
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}