package com.assignment.AccountService.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiErrorTest {

    @Test
    public void givenApiError_whenGettersCalled_thenCorrectValuesReturned() {
        // Given
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Bad request";
        List<String> errors = Arrays.asList("Error 1", "Error 2");
        ApiError apiError = new ApiError(status, message, errors);

        // When
        HttpStatus actualStatus = apiError.getStatus();
        String actualMessage = apiError.getMessage();
        List<String> actualErrors = apiError.getErrors();

        // Then
        assertThat(actualStatus).isEqualTo(status);
        assertThat(actualMessage).isEqualTo(message);
        assertThat(actualErrors).isEqualTo(errors);
    }

    @Test
    public void givenApiErrorWithNullErrors_whenGettersCalled_thenCorrectValuesReturned() {
        // Given
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Bad request";
        ApiError apiError = new ApiError(status, message);

        // When
        HttpStatus actualStatus = apiError.getStatus();
        String actualMessage = apiError.getMessage();
        List<String> actualErrors = apiError.getErrors();

        // Then
        assertThat(actualStatus).isEqualTo(status);
        assertThat(actualMessage).isEqualTo(message);
        assertThat(actualErrors).isNull();
    }

    @Test
    public void givenApiErrorWithNullMessage_whenGettersCalled_thenCorrectValuesReturned() {
        // Given
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(status, null);

        // When
        HttpStatus actualStatus = apiError.getStatus();
        String actualMessage = apiError.getMessage();
        List<String> actualErrors = apiError.getErrors();

        // Then
        assertThat(actualStatus).isEqualTo(status);
        assertThat(actualMessage).isNull();
        assertThat(actualErrors).isNull();
    }
}
