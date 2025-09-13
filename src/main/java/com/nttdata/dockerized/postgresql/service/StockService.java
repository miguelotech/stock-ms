package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.entity.Stock;

import java.util.List;

public interface StockService {

    public List<Stock> listAll();

    public Stock findById(Integer id);

    public Stock save(Stock stock);
    
    public List<Stock> findByProductId(Integer productId);
    
    public void checkStockAvailability(Integer productId, Integer requestedQuantity);
}
