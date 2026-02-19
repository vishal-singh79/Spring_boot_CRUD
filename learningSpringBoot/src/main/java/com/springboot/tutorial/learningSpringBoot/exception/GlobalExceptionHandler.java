package com.springboot.tutorial.learningSpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400 Bad Request
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        if (ex.getMessage().contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404
        } else if (ex.getMessage().contains("already in use")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // 500
    }
}