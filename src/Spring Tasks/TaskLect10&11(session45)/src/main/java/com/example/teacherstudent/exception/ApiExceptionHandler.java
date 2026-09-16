package com.example.teacherstudent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> notFound(ResourceNotFoundException exception) {
        return Map.of("timestamp", Instant.now().toString(), "status", 404, "message", exception.getMessage());
    }
}
