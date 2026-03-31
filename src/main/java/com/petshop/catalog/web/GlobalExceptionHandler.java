package com.petshop.catalog.web;

import com.petshop.catalog.web.exception.InvalidCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<BaseResponse<Object>> handleInvalidCredentials(
            InvalidCredentialsException ex
    ) {
        return ResponseEntity.status(401).body(
                new BaseResponse<>(ex.getMessage(), null)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Object>> handleBadRequest(
            IllegalArgumentException ex
    ) {
        return ResponseEntity.badRequest().body(
                new BaseResponse<>(ex.getMessage(), null)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGeneric(
            Exception ex
    ) {
        return ResponseEntity.status(500).body(
                new BaseResponse<>("Internal server error", null)
        );
    }
}