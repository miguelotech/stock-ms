package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.dto.FindByProductIdDto;
import com.nttdata.dockerized.postgresql.dto.SaveStockRequestDto;
import com.nttdata.dockerized.postgresql.dto.SaveStockResponseDto;
import com.nttdata.dockerized.postgresql.entity.Stock;
import com.nttdata.dockerized.postgresql.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    private Stock stock1;
    private Stock stock2;
    private SaveStockRequestDto saveStockRequestDto;
    private SaveStockResponseDto saveStockResponseDto;

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

        saveStockRequestDto = new SaveStockRequestDto();
        saveStockRequestDto.setProductId(123);
        saveStockRequestDto.setWareHouseId(1);
        saveStockRequestDto.setQuantity(50);

        saveStockResponseDto = new SaveStockResponseDto();
        saveStockResponseDto.setId(1);
        saveStockResponseDto.setProductId(123);
        saveStockResponseDto.setWareHouseId(1);
        saveStockResponseDto.setQuantity(50);
    }

    @Test
    void getStockByProductId_ShouldReturnFindByProductIdDto_WhenStockExists() {
        // Given
        Integer productId = 123;
        List<Stock> stocks = Arrays.asList(stock1, stock2);
        when(stockService.findByProductId(productId)).thenReturn(stocks);

        // When
        FindByProductIdDto result = stockController.getStockByProductId(productId);

        // Then
        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(50, result.getTotal()); // 30 + 20
        verify(stockService, times(1)).findByProductId(productId);
    }

    @Test
    void getStockByProductId_ShouldReturnZeroTotal_WhenSingleStock() {
        // Given
        Integer productId = 123;
        List<Stock> stocks = Arrays.asList(stock1);
        when(stockService.findByProductId(productId)).thenReturn(stocks);

        // When
        FindByProductIdDto result = stockController.getStockByProductId(productId);

        // Then
        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(30, result.getTotal());
        verify(stockService, times(1)).findByProductId(productId);
    }

    @Test
    void getStockByProductId_ShouldReturnZeroTotal_WhenEmptyStockList() {
        // Given
        Integer productId = 123;
        List<Stock> stocks = Arrays.asList();
        when(stockService.findByProductId(productId)).thenReturn(stocks);

        // When
        FindByProductIdDto result = stockController.getStockByProductId(productId);

        // Then
        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(0, result.getTotal());
        verify(stockService, times(1)).findByProductId(productId);
    }

    @Test
    void save_ShouldReturnSaveStockResponseDto_WhenValidRequest() {
        // Given
        when(stockService.save(any(Stock.class))).thenReturn(stock1);

        // When
        SaveStockResponseDto result = stockController.save(saveStockRequestDto);

        // Then
        assertNotNull(result);
        assertEquals(stock1.getId(), result.getId());
        assertEquals(stock1.getProductId(), result.getProductId());
        assertEquals(stock1.getWareHouseId(), result.getWareHouseId());
        assertEquals(stock1.getQuantity(), result.getQuantity());
        verify(stockService, times(1)).save(any(Stock.class));
    }

    @Test
    void save_ShouldCallServiceWithCorrectStock_WhenValidRequest() {
        // Given
        when(stockService.save(any(Stock.class))).thenReturn(stock1);

        // When
        stockController.save(saveStockRequestDto);

        // Then
        verify(stockService, times(1)).save(argThat(stock -> 
            stock.getProductId().equals(saveStockRequestDto.getProductId()) &&
            stock.getWareHouseId().equals(saveStockRequestDto.getWareHouseId()) &&
            stock.getQuantity().equals(saveStockRequestDto.getQuantity())
        ));
    }
}
