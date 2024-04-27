package com.company.CarFactoryManagement.service;

import com.company.CarFactoryManagement.dto.request.FactoryRequestDto;
import com.company.CarFactoryManagement.entity.Factory;
import com.company.CarFactoryManagement.repository.FactoryRepository;
import com.company.CarFactoryManagement.service.impl.FactoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FactoryServiceImplTest {

    @InjectMocks
    FactoryServiceImpl academyService;
    @Mock
    FactoryRepository academyRepository;
    @Mock
    ModelMapper modelMapper;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testIfAllExists() {
        Factory academy1 = new Factory("Tesla", "USA");
        Factory academy2 = new Factory("SpaceX", "USA");
        Factory academy3 = new Factory("BMWMotors", "Germany");

        List<Factory> academies = asList(academy1, academy2, academy3);

        when(academyRepository.findAll()).thenReturn(academies);

        when(modelMapper.map(academy1, com.company.CarFactoryManagement.dto.response.FactoryResponseDto.class))
                        .thenReturn(new com.company.CarFactoryManagement.dto.response.FactoryResponseDto("LTC", "Khatai"));
        when(modelMapper.map(academy2, com.company.CarFactoryManagement.dto.response.FactoryResponseDto.class))
                .thenReturn(new com.company.CarFactoryManagement.dto.response.FactoryResponseDto("BIrainy", "City"));
        when(modelMapper.map(academy3, com.company.CarFactoryManagement.dto.response.FactoryResponseDto.class))
                .thenReturn(new com.company.CarFactoryManagement.dto.response.FactoryResponseDto("Celt", "Ganjlik"));

        List<com.company.CarFactoryManagement.dto.response.FactoryResponseDto> result = academyService.getAll();

        assertEquals(3, result.size());

        assertEquals("LTC", result.get(0).getName());
        assertEquals("Khatai", result.get(0).getLocation());

        assertEquals("BIrainy", result.get(1).getLocation());
        assertEquals("City", result.get(1).getLocation());

    }


    @Test
    public void checkIfAllIsSaved() {
        FactoryRequestDto factoryRequest1 = new FactoryRequestDto("Tesla", "USA");
        FactoryRequestDto factoryRequest2 = new FactoryRequestDto("SpaceX", "USA");
        FactoryRequestDto factoryRequest3 = new FactoryRequestDto("BMWMotors", "Germany");

        Factory factory1 = new Factory("Tesla", "USA");//bunlar sadece mock olaraq goruntu uchun yazilib
        when(modelMapper.map(factoryRequest1, Factory.class)).thenReturn(factory1);

        Factory factory2 = new Factory("SpaceX", "USA");
        when(modelMapper.map(factoryRequest2, Factory.class)).thenReturn(factory2);

        Factory factory3 = new Factory("BMWMotors", "Germany");
        when(modelMapper.map(factoryRequest3, Factory.class)).thenReturn(factory3);


        academyService.create(factoryRequest1);
        academyService.create(factoryRequest2);
        academyService.create(factoryRequest3);

        ArgumentCaptor<Factory> academyCaptor = ArgumentCaptor.forClass(Factory.class);
        verify(academyRepository, times(3)).save(academyCaptor.capture());
        List<Factory> capturedAcademies = academyCaptor.getAllValues();


        assertEquals(3, capturedAcademies.size());//ilk yazdigim, ikinci ise yazilanin olcusunu verir.

        assertEquals("LTC", capturedAcademies.get(0).getName());
        assertEquals("Khatai", capturedAcademies.get(0).getLocation());

        assertEquals("BIrainy", capturedAcademies.get(1).getName());
        assertEquals("City", capturedAcademies.get(1).getLocation());

        assertEquals("Celt", capturedAcademies.get(2).getName());
        assertEquals("Ganjlik", capturedAcademies.get(2).getLocation());
    }


    @Test
    public void testGetById_AcademyFound() {
        Integer academyId = 1;
        Factory academy = new Factory("LTC", "Khatai");

        when(academyRepository.findById(academyId)).thenReturn(Optional.of(academy));

        com.company.CarFactoryManagement.dto.response.FactoryResponseDto academyResponseDto = new com.company.CarFactoryManagement.dto.response.FactoryResponseDto("LTC", "Khatai");
        when(modelMapper.map(academy, com.company.CarFactoryManagement.dto.response.FactoryResponseDto.class)).thenReturn(academyResponseDto);

        ResponseEntity<com.company.CarFactoryManagement.dto.response.FactoryResponseDto> academyResponse = academyService.getById(academyId);

        assertEquals(HttpStatus.OK, academyResponse.getStatusCode());

        assertEquals(academy.getName(), academyResponse.getBody().getName());
        assertEquals(academy.getLocation(), academyResponse.getBody().getLocation());

        verify(academyRepository).findById(academyId);

        verify(modelMapper).map(academy, com.company.CarFactoryManagement.dto.response.FactoryResponseDto.class);
    }




    @Test
    public void testGetById_AcademyNotFound() {
        // Mock data
        Integer academyId = 1;

        // Mocking repository behavior for academy not found
        when(academyRepository.findById(academyId)).thenThrow(new RuntimeException("Academy not found with id: " + academyId));

        // Call the method
        try {
            academyService.getById(academyId);
            fail("Expected an exception to be thrown");
        } catch (RuntimeException e) {
            // Verify that findById was called with the correct ID
            verify(academyRepository).findById(academyId);

            // Verify that modelMapper.map was not called
            verifyNoInteractions(modelMapper);

            // Verify the type of exception thrown
            assertEquals("Academy not found with id: " + academyId, e.getMessage());
        }
    }


    @Test
    public void testIfAcademyIsDeleted() {
        Integer academyId = 1;

        ResponseEntity<Void> response = academyService.deleteById(academyId);

        verify(academyRepository).deleteById(academyId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}


