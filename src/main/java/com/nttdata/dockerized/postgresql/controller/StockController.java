package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.dto.FindByProductIdDto;
import com.nttdata.dockerized.postgresql.dto.SaveStockRequestDto;
import com.nttdata.dockerized.postgresql.dto.SaveStockResponseDto;
import com.nttdata.dockerized.postgresql.entity.Stock;
import com.nttdata.dockerized.postgresql.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.StockMapper.INSTANCE;

@RestController
@RequestMapping("/stock")
// "GET = /stock/{productId}" y "POST = /stock"
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public FindByProductIdDto getStockByProductId(@PathVariable Integer productId) {
        List<Stock> stocks = stockService.findByProductId(productId);
        
        // Calcular el total de cantidad para el productId
        Integer total = stocks.stream()
                .mapToInt(Stock::getQuantity)
                .sum();
        
        FindByProductIdDto response = new FindByProductIdDto();
        response.setProductId(productId);
        response.setTotal(total);
        
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaveStockResponseDto save(@RequestBody SaveStockRequestDto saveStockRequestDto) {
        return INSTANCE.toSaveStockResponseDto(stockService.save(INSTANCE.toEntityFromRequest(saveStockRequestDto)));
    }
}
