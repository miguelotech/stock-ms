package com.nttdata.dockerized.postgresql.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindByProductIdDtoTest {

    @Test
    void constructor_ShouldCreateEmptyObject() {
        // When
        FindByProductIdDto dto = new FindByProductIdDto();

        // Then
        assertNotNull(dto);
        assertNull(dto.getProductId());
        assertNull(dto.getTotal());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        // Given
        FindByProductIdDto dto = new FindByProductIdDto();
        Integer productId = 123;
        Integer total = 50;

        // When
        dto.setProductId(productId);
        dto.setTotal(total);

        // Then
        assertEquals(productId, dto.getProductId());
        assertEquals(total, dto.getTotal());
    }

    @Test
    void settersAndGetters_WithNullValues_ShouldWorkCorrectly() {
        // Given
        FindByProductIdDto dto = new FindByProductIdDto();

        // When
        dto.setProductId(null);
        dto.setTotal(null);

        // Then
        assertNull(dto.getProductId());
        assertNull(dto.getTotal());
    }

    @Test
    void settersAndGetters_WithZeroValues_ShouldWorkCorrectly() {
        // Given
        FindByProductIdDto dto = new FindByProductIdDto();
        Integer productId = 0;
        Integer total = 0;

        // When
        dto.setProductId(productId);
        dto.setTotal(total);

        // Then
        assertEquals(productId, dto.getProductId());
        assertEquals(total, dto.getTotal());
    }

    @Test
    void settersAndGetters_WithNegativeValues_ShouldWorkCorrectly() {
        // Given
        FindByProductIdDto dto = new FindByProductIdDto();
        Integer productId = -1;
        Integer total = -10;

        // When
        dto.setProductId(productId);
        dto.setTotal(total);

        // Then
        assertEquals(productId, dto.getProductId());
        assertEquals(total, dto.getTotal());
    }

    @Test
    void settersAndGetters_WithLargeValues_ShouldWorkCorrectly() {
        // Given
        FindByProductIdDto dto = new FindByProductIdDto();
        Integer productId = Integer.MAX_VALUE;
        Integer total = Integer.MAX_VALUE;

        // When
        dto.setProductId(productId);
        dto.setTotal(total);

        // Then
        assertEquals(productId, dto.getProductId());
        assertEquals(total, dto.getTotal());
    }

    @Test
    void settersAndGetters_WithMinimumValues_ShouldWorkCorrectly() {
        // Given
        FindByProductIdDto dto = new FindByProductIdDto();
        Integer productId = Integer.MIN_VALUE;
        Integer total = Integer.MIN_VALUE;

        // When
        dto.setProductId(productId);
        dto.setTotal(total);

        // Then
        assertEquals(productId, dto.getProductId());
        assertEquals(total, dto.getTotal());
    }
}
