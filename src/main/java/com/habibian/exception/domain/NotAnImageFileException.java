package com.habibian.exception.domain;

public class NotAnImageFileException extends RuntimeException {
    public NotAnImageFileException(String message) {
        super(message);
    }
}
