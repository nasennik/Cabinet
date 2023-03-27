package com.example.cabinet.exception;

public class ServiceError extends Error{

    public ServiceError(String message) {
        super(message);
    }

    public ServiceError(String message, Throwable cause) {
        super(message, cause);
    }
}
