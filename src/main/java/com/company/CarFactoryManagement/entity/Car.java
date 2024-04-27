package com.company.CarFactoryManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String brand;
    private String model;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    @JsonIgnore
    private Factory factory;

}
