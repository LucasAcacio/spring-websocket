package com.artesanalwebsocket.websocket.repository;

import com.artesanalwebsocket.websocket.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {}
