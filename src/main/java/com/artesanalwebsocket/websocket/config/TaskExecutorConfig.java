package com.artesanalwebsocket.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

    @Primary
    @Bean(name="taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setPrestartAllCoreThreads(true);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setQueueCapacity(1000000);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        return taskExecutor;
    }
}

//300000/s
//30000msg/s
