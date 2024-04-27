package com.company.CarFactoryManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponseDto {

    private Integer id;
    private String brand;
    private String model;
    private Integer year;
}
