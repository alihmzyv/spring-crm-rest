package com.example.web_customer_tracker.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.awt.font.NumericShaper;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleNotFoundException(NotFoundException exc) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAll(Throwable exc) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage()));
    }

    public static List<CustomerErrorResponse> getErrorResponses(Errors errors) {
        return errors.getAllErrors().stream()
                .map(objectError ->
                        new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage()))
                .toList();
    }
}
