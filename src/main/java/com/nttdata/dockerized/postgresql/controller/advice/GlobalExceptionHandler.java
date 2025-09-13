package com.nttdata.dockerized.postgresql.controller.advice;

import com.nttdata.dockerized.postgresql.dto.ErrorDetailDto;
import com.nttdata.dockerized.postgresql.exception.InsufficientStockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetailDto handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        return errorDetail;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetailDto handleValidationException(ValidationException ex) {
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        return errorDetail;
    }

    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDetailDto handleInsufficientStockException(InsufficientStockException ex) {
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        return errorDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetailDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        errorDetail.setMessage("Validation failed: " + ex.getBindingResult().getFieldError().getDefaultMessage());
        errorDetail.setDateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        return errorDetail;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetailDto handleGlobalException(Exception ex) {
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        errorDetail.setMessage("An unexpected error occurred: " + ex.getMessage());
        errorDetail.setDateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        return errorDetail;
    }
}
