package com.artesanalwebsocket.websocket.config;

import com.artesanalwebsocket.websocket.handler.websocket.MySessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public StandardWebSocketClient standardWebSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public WebSocketClient webSocketClient() throws URISyntaxException {
        WebSocketClient wsClient = new StandardWebSocketClient();
        wsClient.execute(mySessionHandler, new WebSocketHttpHeaders(), new URI("ws://localhost:8765"));
        return wsClient;
    }
}
