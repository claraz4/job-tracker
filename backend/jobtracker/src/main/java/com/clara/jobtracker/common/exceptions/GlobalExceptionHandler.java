package com.clara.jobtracker.common.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problemDetail.setTitle("Resource Not Found");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicateResourceException(DuplicateResourceException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );

        problemDetail.setTitle("Duplicate Resource");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> fieldErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Validation failed"
        );

        problemDetail.setTitle("Invalid Request");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", fieldErrors);

        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Validation failed"
        );

        problemDetail.setTitle("Invalid Request");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }
}
