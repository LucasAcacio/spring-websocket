package com.artesanalwebsocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.WebSocketHttpHeaders;
import com.artesanalwebsocket.websocket.handler.websocket.MySessionHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Component
public class WSClient {

    Logger logger = LoggerFactory.getLogger(WSClient.class);

    ArrayList<String> messages = new ArrayList<>();
    WebSocketClient webSocketClient;

    public WSClient() throws URISyntaxException {

        this.webSocketClient = new StandardWebSocketClient();
        webSocketClient.execute(new MySessionHandler(), new WebSocketHttpHeaders(), new URI("ws://localhost:8765"));
    }

//    @Scheduled(cron = "*/1 * * * * *")
//    public void publishToRedis() {
//        logger.info("Publishing messages to Redis array length: " + (long) this.messages.size());
//        redisService.bulkInsert(this.messages);
//        this.messages.clear();
//        System.gc();
//    }
}
