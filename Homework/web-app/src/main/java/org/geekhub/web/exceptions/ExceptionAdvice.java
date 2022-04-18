package org.geekhub.web.exceptions;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionAdvice {
    private final Logger logger = new Logger();

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidArgumentException.class)
    public ErrorDto handleInvalidArgumentException(
        InvalidArgumentException e
    ) {
        String message = e.getMessage();
        logger.error(getClass().getSimpleName(), message, e);
        return new ErrorDto(message);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(
        NotFoundException e
    ) {
        String message = e.getMessage();
        logger.error(getClass().getSimpleName(), message, e);
        return new ErrorDto(message);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorDto handleValidationException(
        ValidationException e
    ) {
        String message = e.getMessage();
        logger.error(getClass().getSimpleName(), message, e);
        return new ErrorDto(message);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto handleIllegalArgumentException(
        IllegalArgumentException e
    ) {
        String message = e.getMessage();
        logger.error(getClass().getSimpleName(), message, e);
        return new ErrorDto(message);
    }
}
