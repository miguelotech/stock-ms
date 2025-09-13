package com.nttdata.dockerized.postgresql.dto;

import lombok.Getter;
import lombok.Setter;

//example: OrderedMap { "message": "Quantity of product cannot be 0", "dateTime": "2020-09-18 14:05:23" }
@Getter
@Setter
public class ErrorDetailDto {
    
    private String message;
    private String dateTime;
}
