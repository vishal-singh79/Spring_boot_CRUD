package com.springboot.tutorial.learningSpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice means this class handles errors for ALL controllers
// Instead of crashing with a confusing error, we send a clean JSON response
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation errors (e.g., missing fields, wrong email format)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400 Bad Request
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Loop through all validation errors and collect them
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // Return all errors as JSON like: { "email": "Email is required", ... }
        return errors;
    }

    // Handles our custom RuntimeExceptions (like "Student not found" or "Email already in use")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        // Decide which HTTP status to return based on the message
        if (ex.getMessage().contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404
        } else if (ex.getMessage().contains("already in use")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // 500
    }
}