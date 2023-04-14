package com.example.cabinet.exception;

public class RepositoryException extends RuntimeException{

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}