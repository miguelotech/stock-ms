package com.nttdata.dockerized.postgresql.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockDto {

    private Integer id;

    private Integer productId;

    private Integer wareHouseId;

    private Integer quantity;
}
