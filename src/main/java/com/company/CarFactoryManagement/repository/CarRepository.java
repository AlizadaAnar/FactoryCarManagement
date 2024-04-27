package com.company.CarFactoryManagement.repository;

import com.company.CarFactoryManagement.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findCarByYear(int year);

}
