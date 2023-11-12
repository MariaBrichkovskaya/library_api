package com.example.library_service.service;

import com.example.library_service.dto.CreationRequest;
import com.example.library_service.dto.LibraryDTO;

import java.util.List;

public interface LibraryService extends AbstractService<Long> {
    List<Object> findAvailableBooks();
    void changeAvailability(Long id);
    void setToken(String token);
}
