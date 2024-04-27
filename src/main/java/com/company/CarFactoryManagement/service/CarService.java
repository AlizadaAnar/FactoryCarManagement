package com.company.CarFactoryManagement.service;

import com.company.CarFactoryManagement.dto.request.CarRequestDto;
import com.company.CarFactoryManagement.dto.response.CarResponseDto;
import com.company.CarFactoryManagement.entity.Car;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {

    public List<CarResponseDto> getAll();

    public void create(CarRequestDto carRequestDto);

    public ResponseEntity<CarResponseDto> getById(Integer id);

    ResponseEntity<Car> deleteById(Integer id);

    public void updateCar(Integer id, CarRequestDto carRequestDto);

    public List<Car> findByYear(int year);

}
