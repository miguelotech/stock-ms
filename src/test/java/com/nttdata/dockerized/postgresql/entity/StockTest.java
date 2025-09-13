package com.nttdata.dockerized.postgresql.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void constructor_ShouldCreateEmptyObject() {
        // When
        Stock stock = new Stock();

        // Then
        assertNotNull(stock);
        assertNull(stock.getId());
        assertNull(stock.getProductId());
        assertNull(stock.getWareHouseId());
        assertNull(stock.getQuantity());
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        // Given
        Stock stock = new Stock();
        Integer id = 1;
        Integer productId = 123;
        Integer wareHouseId = 1;
        Integer quantity = 50;

        // When
        stock.setId(id);
        stock.setProductId(productId);
        stock.setWareHouseId(wareHouseId);
        stock.setQuantity(quantity);

        // Then
        assertEquals(id, stock.getId());
        assertEquals(productId, stock.getProductId());
        assertEquals(wareHouseId, stock.getWareHouseId());
        assertEquals(quantity, stock.getQuantity());
    }

    @Test
    void settersAndGetters_WithNullValues_ShouldWorkCorrectly() {
        // Given
        Stock stock = new Stock();

        // When
        stock.setId(null);
        stock.setProductId(null);
        stock.setWareHouseId(null);
        stock.setQuantity(null);

        // Then
        assertNull(stock.getId());
        assertNull(stock.getProductId());
        assertNull(stock.getWareHouseId());
        assertNull(stock.getQuantity());
    }

    @Test
    void settersAndGetters_WithZeroValues_ShouldWorkCorrectly() {
        // Given
        Stock stock = new Stock();
        Integer id = 0;
        Integer productId = 0;
        Integer wareHouseId = 0;
        Integer quantity = 0;

        // When
        stock.setId(id);
        stock.setProductId(productId);
        stock.setWareHouseId(wareHouseId);
        stock.setQuantity(quantity);

        // Then
        assertEquals(id, stock.getId());
        assertEquals(productId, stock.getProductId());
        assertEquals(wareHouseId, stock.getWareHouseId());
        assertEquals(quantity, stock.getQuantity());
    }

    @Test
    void settersAndGetters_WithNegativeValues_ShouldWorkCorrectly() {
        // Given
        Stock stock = new Stock();
        Integer id = -1;
        Integer productId = -1;
        Integer wareHouseId = -5;
        Integer quantity = -10;

        // When
        stock.setId(id);
        stock.setProductId(productId);
        stock.setWareHouseId(wareHouseId);
        stock.setQuantity(quantity);

        // Then
        assertEquals(id, stock.getId());
        assertEquals(productId, stock.getProductId());
        assertEquals(wareHouseId, stock.getWareHouseId());
        assertEquals(quantity, stock.getQuantity());
    }

    @Test
    void settersAndGetters_WithLargeValues_ShouldWorkCorrectly() {
        // Given
        Stock stock = new Stock();
        Integer id = Integer.MAX_VALUE;
        Integer productId = Integer.MAX_VALUE;
        Integer wareHouseId = Integer.MAX_VALUE;
        Integer quantity = Integer.MAX_VALUE;

        // When
        stock.setId(id);
        stock.setProductId(productId);
        stock.setWareHouseId(wareHouseId);
        stock.setQuantity(quantity);

        // Then
        assertEquals(id, stock.getId());
        assertEquals(productId, stock.getProductId());
        assertEquals(wareHouseId, stock.getWareHouseId());
        assertEquals(quantity, stock.getQuantity());
    }

    @Test
    void settersAndGetters_WithMinimumValues_ShouldWorkCorrectly() {
        // Given
        Stock stock = new Stock();
        Integer id = Integer.MIN_VALUE;
        Integer productId = Integer.MIN_VALUE;
        Integer wareHouseId = Integer.MIN_VALUE;
        Integer quantity = Integer.MIN_VALUE;

        // When
        stock.setId(id);
        stock.setProductId(productId);
        stock.setWareHouseId(wareHouseId);
        stock.setQuantity(quantity);

        // Then
        assertEquals(id, stock.getId());
        assertEquals(productId, stock.getProductId());
        assertEquals(wareHouseId, stock.getWareHouseId());
        assertEquals(quantity, stock.getQuantity());
    }
}
