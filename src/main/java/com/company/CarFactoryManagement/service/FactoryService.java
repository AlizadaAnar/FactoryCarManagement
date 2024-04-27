package com.company.CarFactoryManagement.service;

import com.company.CarFactoryManagement.dto.request.FactoryRequestDto;
import com.company.CarFactoryManagement.dto.response.FactoryResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FactoryService {


    public List<FactoryResponseDto> getAll();

    public void create(FactoryRequestDto factoryRequestDto);

    public ResponseEntity<FactoryResponseDto> getById(Integer id);

    public ResponseEntity<Void> deleteById(Integer id);
    public void updateFactory(Integer id, FactoryRequestDto factoryRequestDto);

    public FactoryResponseDto getFactoryWithCars(Integer factoryId);



}
