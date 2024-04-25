package com.artesanalwebsocket.websocket;


import com.artesanalwebsocket.websocket.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.URISyntaxException;

@Import(RedisConfig.class)
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableRedisRepositories
public class WebsocketApplication {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(WebsocketApplication.class, args);
	}
}
