package com.example.books_service.exceptions.handler;

import com.example.books_service.exceptions.response.ExceptionResponse;
import com.example.books_service.exceptions.NotCreatedException;
import com.example.books_service.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.NOT_FOUND,
                        notFoundException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
    @ExceptionHandler(value = {NotCreatedException.class})
    public ResponseEntity<Object> handleNotCreatedException(NotCreatedException notCreatedException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.BAD_REQUEST,
                        notCreatedException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
}
