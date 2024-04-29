package com.artesanalwebsocket.websocket.service;

import com.artesanalwebsocket.websocket.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Async
    public void createBooks(List<Book> bookList) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            long startTime = System.currentTimeMillis();
            for (Book book : bookList) {

                connection.hashCommands().hSet(book.getSymb().getBytes(), "ev".getBytes(), book.getEv().getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "feed".getBytes(), book.getFeed().getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "symb".getBytes(), book.getSymb().getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "bid_px".getBytes(), (book.getBid_px() != null) ? book.getBid_px().toString().getBytes() : "".getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "bid_qty".getBytes(), (book.getBid_qty() != null) ? book.getBid_qty().toString().getBytes() : "".getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "bit_datetime".getBytes(), (book.getBid_datetime() != null) ? book.getBid_datetime().getBytes() : "".getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "offer_px".getBytes(), (book.getOffer_px() != null) ? book.getOffer_px().toString().getBytes() : "".getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "offer_qty".getBytes(), (book.getOffer_qty() != null) ? book.getOffer_qty().toString().getBytes() : "".getBytes());
                connection.hashCommands().hSet(book.getSymb().getBytes(), "offer_datetime".getBytes(), (book.getOffer_datetime() != null) ? book.getOffer_datetime().getBytes() : "".getBytes());
            }
            long endTime = System.currentTimeMillis();

            long totalTime = endTime - startTime;
            logger.info("Created " + bookList.size() + " books in " + totalTime + " ms");
            return null;
        });
    }
}
