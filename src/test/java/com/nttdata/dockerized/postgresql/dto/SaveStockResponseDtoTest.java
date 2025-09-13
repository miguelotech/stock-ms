package com.nttdata.dockerized.postgresql.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveStockResponseDtoTest {

    @Test
    void constructor_ShouldCreateEmptyObject() {
        // When
        SaveStockResponseDto dto = new SaveStockResponseDto();

        // Then
        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getProductId());
        assertNull(dto.getWareHouseId());
        assertNull(dto.getQuantity());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        // Given
        SaveStockResponseDto dto = new SaveStockResponseDto();
        Integer id = 1;
        Integer productId = 123;
        Integer wareHouseId = 1;
        Integer quantity = 50;

        // When
        dto.setId(id);
        dto.setProductId(productId);
        dto.setWareHouseId(wareHouseId);
        dto.setQuantity(quantity);

        // Then
        assertEquals(id, dto.getId());
        assertEquals(productId, dto.getProductId());
        assertEquals(wareHouseId, dto.getWareHouseId());
        assertEquals(quantity, dto.getQuantity());
    }

    @Test
    void settersAndGetters_WithNullValues_ShouldWorkCorrectly() {
        // Given
        SaveStockResponseDto dto = new SaveStockResponseDto();

        // When
        dto.setId(null);
        dto.setProductId(null);
        dto.setWareHouseId(null);
        dto.setQuantity(null);

        // Then
        assertNull(dto.getId());
        assertNull(dto.getProductId());
        assertNull(dto.getWareHouseId());
        assertNull(dto.getQuantity());
    }

    @Test
    void settersAndGetters_WithZeroValues_ShouldWorkCorrectly() {
        // Given
        SaveStockResponseDto dto = new SaveStockResponseDto();
        Integer id = 0;
        Integer productId = 0;
        Integer wareHouseId = 0;
        Integer quantity = 0;

        // When
        dto.setId(id);
        dto.setProductId(productId);
        dto.setWareHouseId(wareHouseId);
        dto.setQuantity(quantity);

        // Then
        assertEquals(id, dto.getId());
        assertEquals(productId, dto.getProductId());
        assertEquals(wareHouseId, dto.getWareHouseId());
        assertEquals(quantity, dto.getQuantity());
    }

    @Test
    void settersAndGetters_WithNegativeValues_ShouldWorkCorrectly() {
        // Given
        SaveStockResponseDto dto = new SaveStockResponseDto();
        Integer id = -1;
        Integer productId = -1;
        Integer wareHouseId = -5;
        Integer quantity = -10;

        // When
        dto.setId(id);
        dto.setProductId(productId);
        dto.setWareHouseId(wareHouseId);
        dto.setQuantity(quantity);

        // Then
        assertEquals(id, dto.getId());
        assertEquals(productId, dto.getProductId());
        assertEquals(wareHouseId, dto.getWareHouseId());
        assertEquals(quantity, dto.getQuantity());
    }

    @Test
    void settersAndGetters_WithLargeValues_ShouldWorkCorrectly() {
        // Given
        SaveStockResponseDto dto = new SaveStockResponseDto();
        Integer id = Integer.MAX_VALUE;
        Integer productId = Integer.MAX_VALUE;
        Integer wareHouseId = Integer.MAX_VALUE;
        Integer quantity = Integer.MAX_VALUE;

        // When
        dto.setId(id);
        dto.setProductId(productId);
        dto.setWareHouseId(wareHouseId);
        dto.setQuantity(quantity);

        // Then
        assertEquals(id, dto.getId());
        assertEquals(productId, dto.getProductId());
        assertEquals(wareHouseId, dto.getWareHouseId());
        assertEquals(quantity, dto.getQuantity());
    }

    @Test
    void settersAndGetters_WithMinimumValues_ShouldWorkCorrectly() {
        // Given
        SaveStockResponseDto dto = new SaveStockResponseDto();
        Integer id = Integer.MIN_VALUE;
        Integer productId = Integer.MIN_VALUE;
        Integer wareHouseId = Integer.MIN_VALUE;
        Integer quantity = Integer.MIN_VALUE;

        // When
        dto.setId(id);
        dto.setProductId(productId);
        dto.setWareHouseId(wareHouseId);
        dto.setQuantity(quantity);

        // Then
        assertEquals(id, dto.getId());
        assertEquals(productId, dto.getProductId());
        assertEquals(wareHouseId, dto.getWareHouseId());
        assertEquals(quantity, dto.getQuantity());
    }
}
