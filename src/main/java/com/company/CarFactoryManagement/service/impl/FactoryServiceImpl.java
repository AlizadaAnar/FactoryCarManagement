package com.company.CarFactoryManagement.service.impl;

import com.company.CarFactoryManagement.dto.request.FactoryRequestDto;
import com.company.CarFactoryManagement.dto.response.FactoryResponseDto;
import com.company.CarFactoryManagement.entity.Factory;
import com.company.CarFactoryManagement.repository.FactoryRepository;
import com.company.CarFactoryManagement.repository.CarRepository;
import com.company.CarFactoryManagement.service.FactoryService;
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
public class FactoryServiceImpl implements FactoryService {

    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final FactoryRepository factoryRepository;


    @Override
    public List<FactoryResponseDto> getAll() {
        List<Factory> all = factoryRepository.findAll();

        return all.stream()
                .map(factory -> modelMapper.map(factory, FactoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(FactoryRequestDto factoryRequestDto) {
        Factory factory = modelMapper.map(factoryRequestDto, Factory.class);

        factoryRepository.save(factory);
    }

    @Override
    public void updateFactory(Integer id, FactoryRequestDto factoryRequestDto) {
        Factory factory = factoryRepository.findById(id).orElseThrow();

        modelMapper.map(factoryRequestDto, factory);
        factoryRepository.save(factory);
    }

    @Override
    public ResponseEntity<FactoryResponseDto> getById(Integer id) {
        Factory factory = factoryRepository.findById(id).orElseThrow();

        return new ResponseEntity<>(modelMapper.map(factory, FactoryResponseDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        factoryRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public FactoryResponseDto getFactoryWithCars(Integer factoryId) {
        Factory factory = factoryRepository.findById(factoryId).orElseThrow();

        FactoryResponseDto response = new FactoryResponseDto();
        response.setName(factory.getName());
        response.setLocation(factory.getLocation());
        response.setCars(factory.getCars());

        return response;
    }
}
