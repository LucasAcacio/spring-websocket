package com.artesanalwebsocket.websocket.service;

import com.artesanalwebsocket.websocket.model.Book;
import com.artesanalwebsocket.websocket.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String BOOKS_KEY = "books";

    @Autowired
    public RedisService(BookRepository bookRepository, RedisTemplate<String, Object> redisTemplate) {
        this.bookRepository = bookRepository;
        this.redisTemplate = redisTemplate;
    }

    @Async
    public void createBook(String book) {
        bookRepository.save(new Book(book));
    }

    @Async
    public void createBooks(List<String> bookList) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (String book : bookList) {
                connection.rPush(BOOKS_KEY.getBytes(), book.getBytes());
            }
            logger.info("Created " + bookList.size() + " books");
            return null;
        });
    }

    public void bulkInsert(List<String> books) {
        bookRepository.saveAll(books.stream()
                .map(Book::new)
                .collect(Collectors.toSet()));
    }
}
