package com.example.pc.exception;

public class ForbiddenStoreActionException extends RuntimeException {
    public ForbiddenStoreActionException(String message) {
        super(message);
    }
}
