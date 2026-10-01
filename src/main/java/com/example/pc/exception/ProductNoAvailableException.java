package com.example.pc.exception;

public class ProductNoAvailableException extends RuntimeException {
    public ProductNoAvailableException(String message) {
        super(message);
    }
}
