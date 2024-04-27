package com.company.CarFactoryManagement.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequestDto {

    private String brand;
    private String model;
    private Integer year;
    public Integer factoryId;
}
