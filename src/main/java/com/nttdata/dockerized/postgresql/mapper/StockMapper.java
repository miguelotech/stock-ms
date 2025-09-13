package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.dto.*;
import com.nttdata.dockerized.postgresql.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    public StockDto map(Stock stock);

    public List<StockDto> map(List<Stock> stock);

    public Stock toEntity(SaveStockRequestDto saveStockRequestDto);

    public SaveStockResponseDto toSaveStockResponseDto(Stock stock);

}
