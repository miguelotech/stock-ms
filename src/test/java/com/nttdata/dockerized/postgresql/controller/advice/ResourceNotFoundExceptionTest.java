package com.nttdata.dockerized.postgresql.controller.advice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void constructor_WithMessage_ShouldSetMessage() {
        // Given
        String message = "Resource not found";

        // When
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        // Then
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_WithMessageAndCause_ShouldSetMessageAndCause() {
        // Given
        String message = "Resource not found";
        Throwable cause = new RuntimeException("Root cause");

        // When
        ResourceNotFoundException exception = new ResourceNotFoundException(message, cause);

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void constructor_WithNullMessage_ShouldHandleCorrectly() {
        // When
        ResourceNotFoundException exception = new ResourceNotFoundException((String) null);

        // Then
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_WithNullCause_ShouldHandleCorrectly() {
        // Given
        String message = "Test message";

        // When
        ResourceNotFoundException exception = new ResourceNotFoundException(message, null);

        // Then
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void inheritance_ShouldExtendRuntimeException() {
        // Given
        ResourceNotFoundException exception = new ResourceNotFoundException("Test");

        // Then
        assertTrue(exception instanceof RuntimeException);
    }
}
