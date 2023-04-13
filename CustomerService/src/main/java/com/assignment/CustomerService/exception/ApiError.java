package com.assignment.CustomerService.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private static final Logger logger = LoggerFactory.getLogger(ApiError.class);

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        logger.error("ApiError - Status: {}, Message: {}, Errors: {}", status, message, errors);
    }

    public ApiError(HttpStatus status, String message) {
        this(status, message, null);
        logger.error("ApiError - Status: {}, Message: {}", status, message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
