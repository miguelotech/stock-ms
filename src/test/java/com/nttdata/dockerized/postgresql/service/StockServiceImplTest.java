package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.controller.advice.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.controller.advice.ValidationException;
import com.nttdata.dockerized.postgresql.entity.Stock;
import com.nttdata.dockerized.postgresql.exception.InsufficientStockException;
import com.nttdata.dockerized.postgresql.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock1;
    private Stock stock2;
    private Stock validStock;

    @BeforeEach
    void setUp() {
        stock1 = new Stock();
        stock1.setId(1);
        stock1.setProductId(123);
        stock1.setWareHouseId(1);
        stock1.setQuantity(30);

        stock2 = new Stock();
        stock2.setId(2);
        stock2.setProductId(123);
        stock2.setWareHouseId(2);
        stock2.setQuantity(20);

        validStock = new Stock();
        validStock.setProductId(123);
        validStock.setWareHouseId(1);
        validStock.setQuantity(50);
    }

    @Test
    void listAll_ShouldReturnAllStocks() {
        // Given
        List<Stock> expectedStocks = Arrays.asList(stock1, stock2);
        when(stockRepository.findAll()).thenReturn(expectedStocks);

        // When
        List<Stock> result = stockService.listAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedStocks, result);
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnStock_WhenStockExists() {
        // Given
        Integer id = 1;
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock1));

        // When
        Stock result = stockService.findById(id);

        // Then
        assertNotNull(result);
        assertEquals(stock1, result);
        verify(stockRepository, times(1)).findById(id);
    }

    @Test
    void findById_ShouldThrowResourceNotFoundException_WhenStockDoesNotExist() {
        // Given
        Integer id = 999;
        when(stockRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> stockService.findById(id));
        
        assertEquals("Stock not found with id: 999", exception.getMessage());
        verify(stockRepository, times(1)).findById(id);
    }

    @Test
    void save_ShouldReturnSavedStock_WhenValidStock() {
        // Given
        when(stockRepository.save(any(Stock.class))).thenReturn(stock1);

        // When
        Stock result = stockService.save(validStock);

        // Then
        assertNotNull(result);
        assertEquals(stock1, result);
        verify(stockRepository, times(1)).save(validStock);
    }

    @Test
    void save_ShouldThrowValidationException_WhenStockIsNull() {
        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.save(null));
        
        assertEquals("Stock data cannot be null", exception.getMessage());
        verify(stockRepository, never()).save(any());
    }

    @Test
    void save_ShouldThrowValidationException_WhenProductIdIsNull() {
        // Given
        validStock.setProductId(null);

        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.save(validStock));
        
        assertEquals("Product ID cannot be null", exception.getMessage());
        verify(stockRepository, never()).save(any());
    }

    @Test
    void save_ShouldThrowValidationException_WhenQuantityIsNull() {
        // Given
        validStock.setQuantity(null);

        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.save(validStock));
        
        assertEquals("Quantity of product cannot be 0 or negative", exception.getMessage());
        verify(stockRepository, never()).save(any());
    }

    @Test
    void save_ShouldThrowValidationException_WhenQuantityIsZero() {
        // Given
        validStock.setQuantity(0);

        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.save(validStock));
        
        assertEquals("Quantity of product cannot be 0 or negative", exception.getMessage());
        verify(stockRepository, never()).save(any());
    }

    @Test
    void save_ShouldThrowValidationException_WhenQuantityIsNegative() {
        // Given
        validStock.setQuantity(-5);

        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.save(validStock));
        
        assertEquals("Quantity of product cannot be 0 or negative", exception.getMessage());
        verify(stockRepository, never()).save(any());
    }

    @Test
    void save_ShouldThrowValidationException_WhenWareHouseIdIsNull() {
        // Given
        validStock.setWareHouseId(null);

        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.save(validStock));
        
        assertEquals("Warehouse ID cannot be null", exception.getMessage());
        verify(stockRepository, never()).save(any());
    }

    @Test
    void findByProductId_ShouldReturnStocks_WhenStocksExist() {
        // Given
        Integer productId = 123;
        List<Stock> expectedStocks = Arrays.asList(stock1, stock2);
        when(stockRepository.findByProductId(productId)).thenReturn(expectedStocks);

        // When
        List<Stock> result = stockService.findByProductId(productId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedStocks, result);
        verify(stockRepository, times(1)).findByProductId(productId);
    }

    @Test
    void findByProductId_ShouldThrowValidationException_WhenProductIdIsNull() {
        // When & Then
        ValidationException exception = assertThrows(ValidationException.class, 
            () -> stockService.findByProductId(null));
        
        assertEquals("Product ID cannot be null", exception.getMessage());
        verify(stockRepository, never()).findByProductId(any());
    }

    @Test
    void findByProductId_ShouldThrowResourceNotFoundException_WhenNoStocksFound() {
        // Given
        Integer productId = 999;
        when(stockRepository.findByProductId(productId)).thenReturn(Arrays.asList());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> stockService.findByProductId(productId));
        
        assertEquals("No stock found for product ID: 999", exception.getMessage());
        verify(stockRepository, times(1)).findByProductId(productId);
    }

    @Test
    void checkStockAvailability_ShouldNotThrowException_WhenSufficientStock() {
        // Given
        Integer productId = 123;
        Integer requestedQuantity = 40;
        List<Stock> stocks = Arrays.asList(stock1, stock2); // Total: 50
        when(stockRepository.findByProductId(productId)).thenReturn(stocks);

        // When & Then
        assertDoesNotThrow(() -> stockService.checkStockAvailability(productId, requestedQuantity));
        verify(stockRepository, times(1)).findByProductId(productId);
    }

    @Test
    void checkStockAvailability_ShouldThrowInsufficientStockException_WhenInsufficientStock() {
        // Given
        Integer productId = 123;
        Integer requestedQuantity = 60;
        List<Stock> stocks = Arrays.asList(stock1, stock2); // Total: 50
        when(stockRepository.findByProductId(productId)).thenReturn(stocks);

        // When & Then
        InsufficientStockException exception = assertThrows(InsufficientStockException.class, 
            () -> stockService.checkStockAvailability(productId, requestedQuantity));
        
        assertEquals("Insufficient stock for product 123. Requested: 60, Available: 50", exception.getMessage());
        assertEquals(productId, exception.getProductId());
        assertEquals(requestedQuantity, exception.getRequestedQuantity());
        assertEquals(50, exception.getAvailableQuantity());
        verify(stockRepository, times(1)).findByProductId(productId);
    }

    @Test
    void checkStockAvailability_ShouldThrowResourceNotFoundException_WhenNoStocksFound() {
        // Given
        Integer productId = 999;
        Integer requestedQuantity = 10;
        when(stockRepository.findByProductId(productId)).thenReturn(Arrays.asList());

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, 
            () -> stockService.checkStockAvailability(productId, requestedQuantity));
        
        assertEquals("No stock found for product ID: 999", exception.getMessage());
        verify(stockRepository, times(1)).findByProductId(productId);
    }

    @Test
    void checkStockAvailability_ShouldWorkWithSingleStock() {
        // Given
        Integer productId = 123;
        Integer requestedQuantity = 25;
        List<Stock> stocks = Arrays.asList(stock1); // Total: 30
        when(stockRepository.findByProductId(productId)).thenReturn(stocks);

        // When & Then
        assertDoesNotThrow(() -> stockService.checkStockAvailability(productId, requestedQuantity));
        verify(stockRepository, times(1)).findByProductId(productId);
    }

    @Test
    void checkStockAvailability_ShouldWorkWithExactMatch() {
        // Given
        Integer productId = 123;
        Integer requestedQuantity = 50;
        List<Stock> stocks = Arrays.asList(stock1, stock2); // Total: 50
        when(stockRepository.findByProductId(productId)).thenReturn(stocks);

        // When & Then
        assertDoesNotThrow(() -> stockService.checkStockAvailability(productId, requestedQuantity));
        verify(stockRepository, times(1)).findByProductId(productId);
    }
}
