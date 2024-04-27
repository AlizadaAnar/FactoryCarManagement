package com.company.CarFactoryManagement.controller;

import com.company.CarFactoryManagement.dto.request.CarRequestDto;
import com.company.CarFactoryManagement.dto.response.CarResponseDto;
import com.company.CarFactoryManagement.entity.Car;
import com.company.CarFactoryManagement.service.CarService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
public class CarController {

    private final ModelMapper modelMapper;
    private final CarService service;

    @GetMapping("/getAllCar")
    public List<CarResponseDto> getAll() {
        return service.getAll();
    }

    @PostMapping("/createCar")
    public void create(CarRequestDto studentRequestDto) {
        service.create(studentRequestDto);
    }

    @GetMapping("getCarById")
    public ResponseEntity<CarResponseDto> getById(Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCarById(@PathVariable Integer id) {
        service.deleteById(id);
    }


    @PutMapping("/updateCar")
    public void updateCar(Integer id, CarRequestDto carRequestDto) {
        service.updateCar(id, carRequestDto);
    }

    @GetMapping("/getByYear")
    public List<Car> getByCarYear(Integer year) {
        return service.findByYear(year);
    }
}
