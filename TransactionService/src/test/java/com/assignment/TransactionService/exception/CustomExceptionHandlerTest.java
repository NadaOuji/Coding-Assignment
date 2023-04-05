package com.assignment.TransactionService.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should handle ResourceNotFoundException and return 404 with error message")
    public void handleResourceNotFoundException_ReturnsApiErrorWithNotFoundStatusAndMessage() {
        // Given
        ResourceNotFoundException ex = new ResourceNotFoundException("Transaction not found");
        WebRequest request = mock(WebRequest.class);

        // When
        ResponseEntity<ApiError> responseEntity = customExceptionHandler.handleResourceNotFoundException(ex, request);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Transaction not found");
    }

    @Test
    @DisplayName("Should handle MethodArgumentNotValidException and return 400 with validation errors")
    public void handleMethodArgumentNotValidException_ReturnsApiErrorWithBadRequestStatusAndValidationErrors() {
        // Given
        FieldError fieldError = new FieldError("TransactionDTO", "id", "ID cannot be null");
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(fieldError);

        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException((MethodParameter) null, bindingResult);

        // When
        ResponseEntity<ApiError> responseEntity = customExceptionHandler.handleMethodArgumentNotValidException(ex);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Validation errors");
        assertThat(responseEntity.getBody().getErrors()).containsExactly("Validation failed for field 'id' with value 'null': ID cannot be null");
    }

    @Test
    @DisplayName("Should handle error mapping and return 500 with error message")
    public void handleError_ReturnsApiErrorWithInternalServerErrorStatusAndMessage() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);

        // When
        ResponseEntity<ApiError> responseEntity = customExceptionHandler.handleError(request);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("An error occurred");
    }
}
