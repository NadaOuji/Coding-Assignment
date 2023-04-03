package com.assignment.AccountService.exception;
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

@ControllerAdvice
public class CustomExceptionHandler {

    /** Handle the resource not found exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return createResponseEntity(HttpStatus.NOT_FOUND, apiError);
    }

    /** Handle the error mapping
     * @param request
     * @return
     */
    @RequestMapping("/error")
    public ResponseEntity<ApiError> handleError(HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, apiError);
    }
    /**
     * Handle the method argument not valid exception
     * @param result
     * @return
     */
    private List<String> getErrorMessages(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(this::buildErrorMessage)
                .collect(Collectors.toList());
    }

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = getErrorMessages(result);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    private ResponseEntity<ApiError> createResponseEntity(HttpStatus httpStatus, ApiError apiError) {
        return ResponseEntity.status(httpStatus).body(apiError);
    }

    public String getErrorPath() throws Exception {
        return "/error";
    }
}
