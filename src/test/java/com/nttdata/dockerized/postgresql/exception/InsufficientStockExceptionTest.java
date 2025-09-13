package com.nttdata.dockerized.postgresql.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsufficientStockExceptionTest {

    @Test
    void constructor_WithProductIdAndQuantities_ShouldSetAllFields() {
        // Given
        Integer productId = 123;
        Integer requestedQuantity = 50;
        Integer availableQuantity = 30;

        // When
        InsufficientStockException exception = new InsufficientStockException(productId, requestedQuantity, availableQuantity);

        // Then
        assertEquals(productId, exception.getProductId());
        assertEquals(requestedQuantity, exception.getRequestedQuantity());
        assertEquals(availableQuantity, exception.getAvailableQuantity());
        assertEquals("Insufficient stock for product 123. Requested: 50, Available: 30", exception.getMessage());
    }

    @Test
    void constructor_WithMessage_ShouldSetMessageAndNullFields() {
        // Given
        String message = "Custom error message";

        // When
        InsufficientStockException exception = new InsufficientStockException(message);

        // Then
        assertNull(exception.getProductId());
        assertNull(exception.getRequestedQuantity());
        assertNull(exception.getAvailableQuantity());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void constructor_WithMessageAndCause_ShouldSetMessageAndCause() {
        // Given
        String message = "Custom error message";
        Throwable cause = new RuntimeException("Root cause");

        // When
        InsufficientStockException exception = new InsufficientStockException(message, cause);

        // Then
        assertNull(exception.getProductId());
        assertNull(exception.getRequestedQuantity());
        assertNull(exception.getAvailableQuantity());
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void constructor_WithProductIdAndQuantities_ShouldGenerateCorrectMessage() {
        // Given
        Integer productId = 456;
        Integer requestedQuantity = 100;
        Integer availableQuantity = 75;

        // When
        InsufficientStockException exception = new InsufficientStockException(productId, requestedQuantity, availableQuantity);

        // Then
        String expectedMessage = "Insufficient stock for product 456. Requested: 100, Available: 75";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_WithZeroValues_ShouldHandleCorrectly() {
        // Given
        Integer productId = 0;
        Integer requestedQuantity = 0;
        Integer availableQuantity = 0;

        // When
        InsufficientStockException exception = new InsufficientStockException(productId, requestedQuantity, availableQuantity);

        // Then
        assertEquals(productId, exception.getProductId());
        assertEquals(requestedQuantity, exception.getRequestedQuantity());
        assertEquals(availableQuantity, exception.getAvailableQuantity());
        assertEquals("Insufficient stock for product 0. Requested: 0, Available: 0", exception.getMessage());
    }

    @Test
    void constructor_WithNegativeValues_ShouldHandleCorrectly() {
        // Given
        Integer productId = -1;
        Integer requestedQuantity = -10;
        Integer availableQuantity = -5;

        // When
        InsufficientStockException exception = new InsufficientStockException(productId, requestedQuantity, availableQuantity);

        // Then
        assertEquals(productId, exception.getProductId());
        assertEquals(requestedQuantity, exception.getRequestedQuantity());
        assertEquals(availableQuantity, exception.getAvailableQuantity());
        assertEquals("Insufficient stock for product -1. Requested: -10, Available: -5", exception.getMessage());
    }

    @Test
    void constructor_WithNullMessage_ShouldHandleCorrectly() {
        // When
        InsufficientStockException exception = new InsufficientStockException((String) null);

        // Then
        assertNull(exception.getProductId());
        assertNull(exception.getRequestedQuantity());
        assertNull(exception.getAvailableQuantity());
        assertNull(exception.getMessage());
    }

    @Test
    void constructor_WithNullCause_ShouldHandleCorrectly() {
        // Given
        String message = "Test message";

        // When
        InsufficientStockException exception = new InsufficientStockException(message, null);

        // Then
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void getters_ShouldReturnCorrectValues() {
        // Given
        Integer productId = 999;
        Integer requestedQuantity = 200;
        Integer availableQuantity = 150;
        InsufficientStockException exception = new InsufficientStockException(productId, requestedQuantity, availableQuantity);

        // When & Then
        assertEquals(productId, exception.getProductId());
        assertEquals(requestedQuantity, exception.getRequestedQuantity());
        assertEquals(availableQuantity, exception.getAvailableQuantity());
    }

    @Test
    void inheritance_ShouldExtendRuntimeException() {
        // Given
        InsufficientStockException exception = new InsufficientStockException("Test");

        // Then
        assertTrue(exception instanceof RuntimeException);
    }
}
