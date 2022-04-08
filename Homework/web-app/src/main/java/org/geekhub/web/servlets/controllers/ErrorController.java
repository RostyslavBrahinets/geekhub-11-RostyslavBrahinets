package org.geekhub.web.servlets.controllers;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    private final Logger logger = new Logger();

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> handleInvalidArgumentException(
        InvalidArgumentException e
    ) {
        logger.error(getClass().getSimpleName(), e.getMessage(), e);
        String error = "Error " + HttpStatus.BAD_REQUEST + "! " + e.getMessage() + "!";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
        NotFoundException e
    ) {
        logger.error(getClass().getSimpleName(), e.getMessage(), e);
        String error = "Error " + HttpStatus.NOT_FOUND + "! " + e.getMessage() + "!";
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(
        ValidationException e
    ) {
        logger.error(getClass().getSimpleName(), e.getMessage(), e);
        String error = "Error " + HttpStatus.BAD_REQUEST + "! " + e.getMessage() + "!";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleValidationException(
        IllegalArgumentException e
    ) {
        logger.error(getClass().getSimpleName(), e.getMessage(), e);
        String error = "Error " + HttpStatus.BAD_REQUEST + "! " + e.getMessage() + "!";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
