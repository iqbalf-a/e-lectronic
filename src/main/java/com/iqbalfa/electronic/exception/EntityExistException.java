package com.iqbalfa.electronic.exception;

public class EntityExistException extends RuntimeException {
    public EntityExistException() {
        super("Data already exists");
    }

    public EntityExistException(String message) {
        super(message);
    }
}
