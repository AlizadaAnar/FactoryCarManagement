package com.company.CarFactoryManagement.dto.response;

import com.company.CarFactoryManagement.entity.Car;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactoryResponseDto {

    private Integer id;
    private String name;
    private String location;
    private List<Car> cars;

    public FactoryResponseDto(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
