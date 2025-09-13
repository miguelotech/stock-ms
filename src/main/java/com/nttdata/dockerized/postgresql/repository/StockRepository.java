package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.entity.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    
    List<Stock> findByProductId(Integer productId);
}
