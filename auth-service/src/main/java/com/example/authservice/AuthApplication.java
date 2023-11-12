package com.example.authservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Test Task for Modsen Company",
                version = "1.0.0",
                description = "Сервис для регистрации и получения токена доступа",
                contact = @Contact(
                        name = "Maria Brichkovskaya",
                        email = "brichkovskayam@gmail.com"
                )

        )
)
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
