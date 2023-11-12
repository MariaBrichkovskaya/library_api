package com.example.library_service.controllers;

import com.example.library_service.dto.CreationRequest;
import com.example.library_service.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/library")
@Tag(name = "Контроллер для работы библиотеки",description = "Предназначен для учета свободных и занятых книг")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping
    @Operation(hidden = true)
    public ResponseEntity<String> createBook(@RequestBody Long id) {
        System.err.println(id);
        libraryService.add(id);
        return ResponseEntity.ok("Добавлена книга с id " + id);
    }

    @DeleteMapping("/{id}")
    @Operation(hidden = true)
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        libraryService.delete(id);
        return ResponseEntity.ok("Удалена книга с id " + id);
    }
    @GetMapping("/available")
    @Operation(
            summary = "Получение информации о доступнх книгах"
    )
    public ResponseEntity<List<Object>> availableBooks(){
        return ResponseEntity.ok(libraryService.findAvailableBooks());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение доступности книги по id",
            description = "Если книга была доступна, то ей назначается время когда ее взяли и когда требуется вернуть"
    )
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<String> changeAvailability(@PathVariable Long id,@RequestHeader("Authorization") String token){
        libraryService.setToken(token);
        libraryService.changeAvailability(id);
        return ResponseEntity.ok("Статус книги с id " + id + " изменен");
    }

}
