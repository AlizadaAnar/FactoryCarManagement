package com.company.CarFactoryManagement.controller;

import com.company.CarFactoryManagement.dto.request.FactoryRequestDto;
import com.company.CarFactoryManagement.dto.response.FactoryResponseDto;
import com.company.CarFactoryManagement.service.FactoryService;
import com.company.CarFactoryManagement.service.impl.FactoryServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
public class FactoryController {

    private final ModelMapper modelMapper;
    private final FactoryService service;
    private final FactoryServiceImpl factoryService;


    @GetMapping("/getAllFactory")
    public List<FactoryResponseDto> getAll() {
        return service.getAll();
    }

    @PostMapping("/createFactory")
    public void create(FactoryRequestDto factoryRequestDto) {
        service.create(factoryRequestDto);
    }


    @PutMapping("/updateFactory")
    public void updateAcademy(Integer id, FactoryRequestDto factoryRequestDto) {
        service.updateFactory(id, factoryRequestDto);
    }

    @GetMapping("/getFactory")
    public FactoryResponseDto getFactoryWithCars(Integer factoryId) {
        return service.getFactoryWithCars(factoryId);
    }
}
