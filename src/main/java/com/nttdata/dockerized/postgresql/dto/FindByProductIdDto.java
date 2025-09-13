package com.nttdata.dockerized.postgresql.dto;

import lombok.Getter;
import lombok.Setter;

//example: OrderedMap { "productId": 2, "total": 12 }
@Getter
@Setter
public class FindByProductIdDto {
    
    private Integer productId;
    private Integer total;
}
