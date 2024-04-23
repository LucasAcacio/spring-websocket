package com.artesanalwebsocket.websocket.config;

import com.artesanalwebsocket.websocket.service.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketConfig {

    @Bean
    public ThreadPoolTaskExecutor webSocketTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        return taskExecutor;
    }

    @Bean
    public StandardWebSocketClient standardWebSocketClient() {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        webSocketClient.setTaskExecutor(webSocketTaskExecutor());
        return webSocketClient;
    }
}
