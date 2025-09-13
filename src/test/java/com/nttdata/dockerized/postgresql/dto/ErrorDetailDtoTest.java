package com.nttdata.dockerized.postgresql.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailDtoTest {

    @Test
    void constructor_ShouldCreateEmptyObject() {
        // When
        ErrorDetailDto errorDetail = new ErrorDetailDto();

        // Then
        assertNotNull(errorDetail);
        assertNull(errorDetail.getMessage());
        assertNull(errorDetail.getDateTime());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        // Given
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        String message = "Test error message";
        String dateTime = "2024-01-15 10:30:45";

        // When
        errorDetail.setMessage(message);
        errorDetail.setDateTime(dateTime);

        // Then
        assertEquals(message, errorDetail.getMessage());
        assertEquals(dateTime, errorDetail.getDateTime());
    }

    @Test
    void settersAndGetters_WithNullValues_ShouldWorkCorrectly() {
        // Given
        ErrorDetailDto errorDetail = new ErrorDetailDto();

        // When
        errorDetail.setMessage(null);
        errorDetail.setDateTime(null);

        // Then
        assertNull(errorDetail.getMessage());
        assertNull(errorDetail.getDateTime());
    }

    @Test
    void settersAndGetters_WithEmptyStrings_ShouldWorkCorrectly() {
        // Given
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        String emptyMessage = "";
        String emptyDateTime = "";

        // When
        errorDetail.setMessage(emptyMessage);
        errorDetail.setDateTime(emptyDateTime);

        // Then
        assertEquals(emptyMessage, errorDetail.getMessage());
        assertEquals(emptyDateTime, errorDetail.getDateTime());
    }

    @Test
    void settersAndGetters_WithSpecialCharacters_ShouldWorkCorrectly() {
        // Given
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        String messageWithSpecialChars = "Error: Quantity of product cannot be 0 or negative! @#$%";
        String dateTimeWithSpecialChars = "2024-01-15 10:30:45";

        // When
        errorDetail.setMessage(messageWithSpecialChars);
        errorDetail.setDateTime(dateTimeWithSpecialChars);

        // Then
        assertEquals(messageWithSpecialChars, errorDetail.getMessage());
        assertEquals(dateTimeWithSpecialChars, errorDetail.getDateTime());
    }

    @Test
    void settersAndGetters_WithLongStrings_ShouldWorkCorrectly() {
        // Given
        ErrorDetailDto errorDetail = new ErrorDetailDto();
        String longMessage = "This is a very long error message that contains detailed information about what went wrong during the validation process and should be handled properly by the error detail DTO";
        String longDateTime = "2024-01-15 10:30:45";

        // When
        errorDetail.setMessage(longMessage);
        errorDetail.setDateTime(longDateTime);

        // Then
        assertEquals(longMessage, errorDetail.getMessage());
        assertEquals(longDateTime, errorDetail.getDateTime());
    }
}
