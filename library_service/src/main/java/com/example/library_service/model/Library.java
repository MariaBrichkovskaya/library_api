package com.example.library_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "books")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Library {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "issueDate")
    LocalDateTime issueDate;
    @Column(name = "acceptanceDate")
    LocalDateTime acceptanceDate;

    public Library(Long id) {
        this.id = id;
    }
}
