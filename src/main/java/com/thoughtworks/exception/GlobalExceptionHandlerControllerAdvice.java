package com.thoughtworks.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerControllerAdvice.class);

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception exception) {
        return new ResponseEntity<>("Server is busy", HttpStatus.UNAUTHORIZED);
    }
}
