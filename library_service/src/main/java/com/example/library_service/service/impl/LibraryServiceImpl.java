package com.example.library_service.service.impl;

import com.example.library_service.dto.CreationRequest;
import com.example.library_service.dto.LibraryDTO;
import com.example.library_service.exceptions.NotFoundException;
import com.example.library_service.exceptions.WebClientException;
import com.example.library_service.model.Library;
import com.example.library_service.repositories.LibraryRepository;
import com.example.library_service.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;
    private static final String BOOK_MODULE_URI = "http://localhost:8080/api/books/";
    private final WebClient webClient;
    private String token;

    @Override
    public void add(Long id) {
        //Library library = modelMapper.map(request, Library.class);
        if (libraryRepository.findById(id).isPresent()) {
            throw new NotFoundException("Книга с id "+id+" существует");
        }
        Library library = new Library(id);
        libraryRepository.save(library);
    }

    @Override
    public void delete(Long id) {
        if (libraryRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Книга с id "+id+" отсутствует");
        }
        libraryRepository.deleteById(id);
    }

    @Override
    public List<Object> findAvailableBooks() {
        List<CreationRequest> availableBooksIds = libraryRepository.findAllByIssueDateIsNull().stream().map(id->modelMapper.map(id, CreationRequest.class)).toList();
        List<Object> availableBooks = new ArrayList<>();
        availableBooksIds.forEach(x -> {
            Object book = webClient.get()
                    .uri(BOOK_MODULE_URI + x.getId())
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .doOnError(throwable -> {throw new WebClientException(throwable.getMessage());})
                    .block();
            availableBooks.add(book);
        });

        return availableBooks;
    }

    @Override
    public void changeAvailability(Long id) {
        if (libraryRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Книга с id "+id+" отсутствует");
        }
        Library library = libraryRepository.findById(id).get();
        System.err.println(library);
        LocalDateTime date=library.getIssueDate();
        if(date != null) {
            library.setIssueDate(null);
            library.setAcceptanceDate(null);
        } else {
            library.setIssueDate(LocalDateTime.now());
            library.setAcceptanceDate(LocalDateTime.now().plusMonths(1));
        }
        libraryRepository.save(library);

    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
