package com.nttdata.dockerized.postgresql.controller.advice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationExceptionTest {

    @Test
    void constructor_WithMessage_ShouldSetMessage() {
        // Given
        String message = "Validation failed";

        // When
        ValidationException exception = new ValidationException(message);

        // Then
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_WithMessageAndCause_ShouldSetMessageAndCause() {
        // Given
        String message = "Validation failed";
        Throwable cause = new IllegalArgumentException("Root cause");

        // When
        ValidationException exception = new ValidationException(message, cause);

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void constructor_WithNullMessage_ShouldHandleCorrectly() {
        // When
        ValidationException exception = new ValidationException((String) null);

        // Then
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_WithNullCause_ShouldHandleCorrectly() {
        // Given
        String message = "Test message";

        // When
        ValidationException exception = new ValidationException(message, null);

        // Then
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void inheritance_ShouldExtendRuntimeException() {
        // Given
        ValidationException exception = new ValidationException("Test");

        // Then
        assertTrue(exception instanceof RuntimeException);
    }
}
