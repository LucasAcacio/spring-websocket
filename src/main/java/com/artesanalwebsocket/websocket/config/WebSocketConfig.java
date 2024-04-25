package com.artesanalwebsocket.websocket.config;

import com.artesanalwebsocket.websocket.handler.websocket.MySessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class WebSocketConfig {

    @Autowired
    private MySessionHandler mySessionHandler;

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

    @Bean
    public WebSocketClient webSocketClient() throws URISyntaxException {
        WebSocketClient wsClient = new StandardWebSocketClient();
        wsClient.execute(mySessionHandler, new WebSocketHttpHeaders(), new URI("ws://localhost:8765"));
        return wsClient;
    }
}
