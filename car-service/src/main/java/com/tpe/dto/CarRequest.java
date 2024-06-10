package com.tpe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

    private String brand;

    private String model;

    private Integer doors;

    private Double pricePerHour;

    private Integer age;

}
