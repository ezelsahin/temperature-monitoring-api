package com.qardio.temperaturemonitoring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map> handleNotFoundException(NotFoundException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("Message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> handleException(Exception exception) {
        Map<String, String> response = new HashMap<>();
        response.put("Message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
