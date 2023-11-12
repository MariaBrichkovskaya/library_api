package com.example.books_service.repositories;


import com.example.books_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByIsbn(String isbn);
}
