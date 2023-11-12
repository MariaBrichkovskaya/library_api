package com.example.books_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Test Task for Modsen Company",
                version = "1.0.0",
                description = "CRUD Web API для имитации библиотеки",
                contact = @Contact(
                        name = "Maria Brichkovskaya",
                        email = "brichkovskayam@gmail.com"
                )

        )
)
public class BooksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
