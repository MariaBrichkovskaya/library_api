package com.example.books_service.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
