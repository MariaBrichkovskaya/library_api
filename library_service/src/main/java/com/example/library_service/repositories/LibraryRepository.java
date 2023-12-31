package com.example.library_service.repositories;

import com.example.library_service.dto.LibraryDTO;
import com.example.library_service.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
    List<Library> findAllByIssueDateIsNull();
}
