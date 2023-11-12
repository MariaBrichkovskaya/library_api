package com.example.books_service.services;


import com.example.books_service.dto.BookRequest;
import com.example.books_service.dto.BookResponse;

public interface BookService extends AbstractService<Long, BookRequest, BookResponse>{
    BookResponse findByIsbn(String isbn);
    void setToken(String token);
}
