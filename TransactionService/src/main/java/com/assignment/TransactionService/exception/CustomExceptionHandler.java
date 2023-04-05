package com.assignment.TransactionService.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides global exception handling for the Transaction Service application.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles the ResourceNotFoundException exception.
     *
     * @param ex      the exception to handle
     * @param request the HTTP servlet request
     * @return a ResponseEntity containing an ApiError object and a NOT_FOUND HTTP status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return createResponseEntity(HttpStatus.NOT_FOUND, apiError);
    }

    /**
     * Handles the MethodArgumentNotValidException exception.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity containing an ApiError object and a BAD_REQUEST HTTP status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = getErrorMessages(result);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    /**
     * Handles all other exceptions.
     *
     * @param request the HTTP servlet request
     * @return a ResponseEntity containing an ApiError object and an INTERNAL_SERVER_ERROR HTTP status
     */
    @RequestMapping("/error")
    public ResponseEntity<ApiError> handleError(HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, apiError);
    }

    /**
     * Builds a list of error messages from the given BindingResult object.
     *
     * @param result the BindingResult object to extract error messages from
     * @return a List of error messages
     */
    private List<String> getErrorMessages(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(this::buildErrorMessage)
                .collect(Collectors.toList());
    }

    /**
     * Builds an error message for the given FieldError object.
     *
     * @param error the FieldError object to build an error message for
     * @return a String representing the error message
     */
    private String buildErrorMessage(FieldError error) {
        StringBuilder builder = new StringBuilder();
        builder.append("Validation failed for field '")
                .append(error.getField())
                .append("' with value '")
                .append(error.getRejectedValue())
                .append("': ")
                .append(error.getDefaultMessage());
        return builder.toString();
    }

    /**
     * Creates a ResponseEntity object with the given HttpStatus and ApiError objects.
     *
     * @param httpStatus the HTTP status code to use
     * @param apiError   the ApiError object to include in the response
     * @return a ResponseEntity object with the given HttpStatus and ApiError objects
     */
    private ResponseEntity<ApiError> createResponseEntity(HttpStatus httpStatus, ApiError apiError) {
        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
