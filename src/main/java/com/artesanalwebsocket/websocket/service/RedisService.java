package com.artesanalwebsocket.websocket.service;

import com.artesanalwebsocket.websocket.model.Book;
import com.artesanalwebsocket.websocket.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public RedisService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Async
    public void createBook(String book) {
        bookRepository.save(new Book(book));
    }

    public void bulkInsert(List<String> books) {
        bookRepository.saveAll(books.stream()
                .map(Book::new)
                .collect(Collectors.toSet()));
    }
}
