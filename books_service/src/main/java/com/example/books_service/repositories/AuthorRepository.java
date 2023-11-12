package com.example.books_service.repositories;


import com.example.books_service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);
}
