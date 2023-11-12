package com.example.books_service.controllers;

import com.example.books_service.dto.AuthorDTO;
import com.example.books_service.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
@Tag(name = "Контроллер для работы с авторами",description = "Предназначен для CRUD методов с авторами")
public class AuthorController {
    private final AuthorService authorService;
    @Operation(
            summary = "Получение всех авторов"
    )
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAll(){
        List<AuthorDTO> authors=authorService.findAll();
        return ResponseEntity.ok(authors);
    }
    @PostMapping
    @Operation(
            summary = "Добавление автора"
    )
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.add(authorDTO);
        return ResponseEntity.ok("Добавлен автор " + authorDTO.getName()+" "+authorDTO.getSurname());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление автора по id"
    )
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.ok("Удален автор с id " + id);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об авторе по id"
    )
    public ResponseEntity<AuthorDTO> authorInfo(@PathVariable Long id){
        AuthorDTO author=authorService.findById(id);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование информации об авторе",
            description = "Аргументами являются id изменяемого автора и dto с данными об авторе и уже внесенными изменениями"
    )
    public ResponseEntity<String> updateAuthor(@PathVariable Long id,@RequestBody AuthorDTO authorDTO)
    {
        authorService.update(authorDTO,id);
        return ResponseEntity.ok("Изменен автор с id " + id);
    }
}
