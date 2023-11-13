package com.example.books_service.controllers;


import com.example.books_service.dto.BookRequest;
import com.example.books_service.dto.BookResponse;
import com.example.books_service.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BearerAuth", scheme = "bearer")
@RequestMapping("/api/books")
@Tag(name = "Контроллер для работы с книгами",description = "Предназначен для CRUD методов и поиска по isbn для книг")
public class BookController {
    private final BookService bookService;
    @GetMapping
    @Operation(
            summary = "Получение информации о всех книгах"
    )
    public ResponseEntity<List<BookResponse>> getAll(){
        List<BookResponse> books=bookService.findAll();
        return ResponseEntity.ok(books);
    }
    @PostMapping
    @Operation(
            summary = "Создание книги"
    )
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<String> createBook(@RequestBody BookRequest bookDTO,  @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        bookService.setToken(token);
        bookService.add(bookDTO);
        return ResponseEntity.ok("Добавлена книга " + bookDTO.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление книги по id"
    )
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<String> deleteBook(@PathVariable Long id,@Parameter(hidden = true) @RequestHeader(value = "Authorization") String token){
        bookService.setToken(token);
        bookService.delete(id);
        return ResponseEntity.ok("Удалена книга с id " + id);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации о книге по id"
    )
    public ResponseEntity<BookResponse> bookInfo(@PathVariable Long id){
        BookResponse book=bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование информации о книге",
            description = "Аргументами являются id изменяемой книги и dto с данными о книге и уже внесенными изменениями"
    )
    public ResponseEntity<String> updateBook(@PathVariable Long id,@RequestBody BookRequest bookRequest)
    {
        bookService.update(bookRequest,id);
        return ResponseEntity.ok("Изменена книга с id " + id);
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(
            summary = "Получение информации о книге по isbn"
    )
    public ResponseEntity<BookResponse> findByIsbn(@PathVariable String isbn){
        BookResponse book=bookService.findByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

}
