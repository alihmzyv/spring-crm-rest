package com.example.web_customer_tracker.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleNotFound(NotFoundException exc) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleConstrainViolation(DataIntegrityViolationException exc) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMostSpecificCause().getMessage()));
    }

    public static List<CustomerErrorResponse> getErrorResponses(Errors errors) {
        return errors.getAllErrors().stream()
                .map(objectError ->
                        new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage()))
                .toList();
    }
}
