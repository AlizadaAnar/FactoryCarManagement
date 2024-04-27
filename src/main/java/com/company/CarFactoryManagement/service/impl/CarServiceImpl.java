package com.company.CarFactoryManagement.service.impl;

import com.company.CarFactoryManagement.dto.request.CarRequestDto;
import com.company.CarFactoryManagement.dto.response.CarResponseDto;
import com.company.CarFactoryManagement.entity.Factory;
import com.company.CarFactoryManagement.entity.Car;
import com.company.CarFactoryManagement.repository.FactoryRepository;
import com.company.CarFactoryManagement.repository.CarRepository;
import com.company.CarFactoryManagement.service.CarService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final FactoryRepository factoryRepository;

    @Override
    public List<CarResponseDto> getAll() {
        List<Car> all = carRepository.findAll();

        return all.stream()
                .map(car -> modelMapper.map(car, CarResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(CarRequestDto carRequestDto) {
        Car car = new Car();
        car.setBrand(carRequestDto.getBrand());
        car.setYear(carRequestDto.getYear());
        car.setModel(carRequestDto.getModel());

        Factory academy = factoryRepository.findById(carRequestDto.getFactoryId()).orElseThrow();
        car.setFactory(academy);
        carRepository.save(car);
    }

    @Override
    public ResponseEntity<CarResponseDto> getById(Integer id) {
        Car car = carRepository.findById(id).orElseThrow();

        return new ResponseEntity<>(modelMapper.map(car, CarResponseDto.class), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Car> deleteById(Integer id) {
        carRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public void updateCar(Integer id, CarRequestDto carRequestDto) {
        Car car = carRepository.findById(id).orElseThrow();

        modelMapper.map(carRequestDto, car);
        carRepository.save(car);
    }

    @Override
    public List<Car> findByYear(int year) {
        return carRepository.findAll().stream()
                .filter(car -> car.getYear() == year)
                .collect(Collectors.toList());
    }

}
