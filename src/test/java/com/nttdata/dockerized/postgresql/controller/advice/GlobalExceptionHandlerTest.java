package com.nttdata.dockerized.postgresql.controller.advice;

import com.nttdata.dockerized.postgresql.dto.ErrorDetailDto;
import com.nttdata.dockerized.postgresql.exception.InsufficientStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private MethodArgumentNotValidException methodArgumentNotValidException;
    private BindingResult bindingResult;
    private FieldError fieldError;

    @BeforeEach
    void setUp() {
        methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        bindingResult = mock(BindingResult.class);
        fieldError = mock(FieldError.class);
    }

    @Test
    void handleResourceNotFoundException_ShouldReturnErrorDetailDtoWithNotFoundStatus() {
        // Given
        String message = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleResourceNotFoundException(exception);

        // Then
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertNotNull(result.getDateTime());
        assertTrue(result.getDateTime().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    void handleValidationException_ShouldReturnErrorDetailDtoWithBadRequestStatus() {
        // Given
        String message = "Validation failed";
        ValidationException exception = new ValidationException(message);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleValidationException(exception);

        // Then
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertNotNull(result.getDateTime());
        assertTrue(result.getDateTime().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    void handleInsufficientStockException_ShouldReturnErrorDetailDtoWithConflictStatus() {
        // Given
        String message = "Insufficient stock for product 123. Requested: 50, Available: 30";
        InsufficientStockException exception = new InsufficientStockException(message);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleInsufficientStockException(exception);

        // Then
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertNotNull(result.getDateTime());
        assertTrue(result.getDateTime().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    void handleValidationExceptions_ShouldReturnErrorDetailDtoWithBadRequestStatus() {
        // Given
        String fieldErrorMessage = "Quantity cannot be null";
        when(fieldError.getDefaultMessage()).thenReturn(fieldErrorMessage);
        when(bindingResult.getFieldError()).thenReturn(fieldError);
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        // Then
        assertNotNull(result);
        assertEquals("Validation failed: " + fieldErrorMessage, result.getMessage());
        assertNotNull(result.getDateTime());
        assertTrue(result.getDateTime().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    void handleGlobalException_ShouldReturnErrorDetailDtoWithInternalServerErrorStatus() {
        // Given
        String message = "Unexpected error occurred";
        Exception exception = new RuntimeException(message);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleGlobalException(exception);

        // Then
        assertNotNull(result);
        assertEquals("An unexpected error occurred: " + message, result.getMessage());
        assertNotNull(result.getDateTime());
        assertTrue(result.getDateTime().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    void handleGlobalException_ShouldHandleNullMessage() {
        // Given
        Exception exception = new RuntimeException();

        // When
        ErrorDetailDto result = globalExceptionHandler.handleGlobalException(exception);

        // Then
        assertNotNull(result);
        assertEquals("An unexpected error occurred: null", result.getMessage());
        assertNotNull(result.getDateTime());
    }

    @Test
    void handleResourceNotFoundException_ShouldHandleExceptionWithCause() {
        // Given
        String message = "Resource not found";
        Throwable cause = new RuntimeException("Root cause");
        ResourceNotFoundException exception = new ResourceNotFoundException(message, cause);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleResourceNotFoundException(exception);

        // Then
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertNotNull(result.getDateTime());
    }

    @Test
    void handleValidationException_ShouldHandleExceptionWithCause() {
        // Given
        String message = "Validation failed";
        Throwable cause = new IllegalArgumentException("Root cause");
        ValidationException exception = new ValidationException(message, cause);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleValidationException(exception);

        // Then
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertNotNull(result.getDateTime());
    }

    @Test
    void handleInsufficientStockException_ShouldHandleExceptionWithCause() {
        // Given
        String message = "Insufficient stock";
        Throwable cause = new RuntimeException("Root cause");
        InsufficientStockException exception = new InsufficientStockException(message, cause);

        // When
        ErrorDetailDto result = globalExceptionHandler.handleInsufficientStockException(exception);

        // Then
        assertNotNull(result);
        assertEquals(message, result.getMessage());
        assertNotNull(result.getDateTime());
    }

    @Test
    void allHandlers_ShouldReturnConsistentDateTimeFormat() {
        // Given
        String message = "Test message";
        ResourceNotFoundException resourceException = new ResourceNotFoundException(message);
        ValidationException validationException = new ValidationException(message);
        InsufficientStockException insufficientException = new InsufficientStockException(message);
        Exception globalException = new RuntimeException(message);

        // When
        ErrorDetailDto resourceResult = globalExceptionHandler.handleResourceNotFoundException(resourceException);
        ErrorDetailDto validationResult = globalExceptionHandler.handleValidationException(validationException);
        ErrorDetailDto insufficientResult = globalExceptionHandler.handleInsufficientStockException(insufficientException);
        ErrorDetailDto globalResult = globalExceptionHandler.handleGlobalException(globalException);

        // Then
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        
        assertTrue(resourceResult.getDateTime().matches(dateTimePattern));
        assertTrue(validationResult.getDateTime().matches(dateTimePattern));
        assertTrue(insufficientResult.getDateTime().matches(dateTimePattern));
        assertTrue(globalResult.getDateTime().matches(dateTimePattern));
    }
}
