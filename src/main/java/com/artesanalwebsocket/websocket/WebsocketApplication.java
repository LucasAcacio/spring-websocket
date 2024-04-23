package com.artesanalwebsocket.websocket;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.URISyntaxException;

@SpringBootApplication(scanBasePackages = {"com.artesanalwebsocket.websocket.config"})
@EnableScheduling
@EnableAsync
public class WebsocketApplication {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(WebsocketApplication.class, args);

//		WebSocketClient webSocketClient = new StandardWebSocketClient();
//		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
//		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//		stompClient.setTaskScheduler(threadPoolTaskScheduler()); // for heartbeats
//		stompClient.setInboundMessageSizeLimit(64 * 1024 * 1024);
//
//		String url = "ws://localhost:8765/";
//		StompSessionHandler sessionHandler = new MyStompSessionHandler();
//		CompletableFuture<StompSession> temp = stompClient.connectAsync(url, sessionHandler);
//		temp.join();
//		System.out.println("Connected 1");

		new WSClient();
	}
}
