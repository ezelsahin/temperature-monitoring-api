package com.qardio.temperaturemonitoring.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message + " is not found!");
    }
}
