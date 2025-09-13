package com.nttdata.dockerized.postgresql.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStockResponseDto {

    private Integer id;

    private Integer productId;

    private Integer wareHouseId;

    private Integer quantity;
}
