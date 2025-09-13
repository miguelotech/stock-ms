package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.controller.advice.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.controller.advice.ValidationException;
import com.nttdata.dockerized.postgresql.entity.Stock;
import com.nttdata.dockerized.postgresql.exception.InsufficientStockException;
import com.nttdata.dockerized.postgresql.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> listAll() { return (List<Stock>) stockRepository.findAll();}

    @Override
    public Stock findById(Integer id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with id: " + id));
    }

    @Override
    public Stock save(Stock stock) {
        validateStockData(stock);
        return stockRepository.save(stock);
    }
    
    @Override
    public List<Stock> findByProductId(Integer productId) {
        if (productId == null) {
            throw new ValidationException("Product ID cannot be null");
        }
        List<Stock> stocks = stockRepository.findByProductId(productId);
        if (stocks.isEmpty()) {
            throw new ResourceNotFoundException("No stock found for product ID: " + productId);
        }
        return stocks;
    }
    
    private void validateStockData(Stock stock) {
        if (stock == null) {
            throw new ValidationException("Stock data cannot be null");
        }
        if (stock.getProductId() == null) {
            throw new ValidationException("Product ID cannot be null");
        }
        if (stock.getQuantity() == null || stock.getQuantity() <= 0) {
            throw new ValidationException("Quantity of product cannot be 0 or negative");
        }
        if (stock.getWareHouseId() == null) {
            throw new ValidationException("Warehouse ID cannot be null");
        }
    }
    
    public void checkStockAvailability(Integer productId, Integer requestedQuantity) {
        List<Stock> stocks = stockRepository.findByProductId(productId);
        if (stocks.isEmpty()) {
            throw new ResourceNotFoundException("No stock found for product ID: " + productId);
        }
        
        Integer availableQuantity = stocks.stream()
                .mapToInt(Stock::getQuantity)
                .sum();
        
        if (availableQuantity < requestedQuantity) {
            throw new InsufficientStockException(productId, requestedQuantity, availableQuantity);
        }
    }
}