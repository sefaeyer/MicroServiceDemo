package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.dto.CarRequest;
import com.tpe.respository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public void createCar(CarRequest carRequest) {

        //Car car = modelMapper.map(carRequest, Car.class);

        carRepository.save(modelMapper.map(carRequest, Car.class));

        // LOG ISLEMLERI YAPILACAK


    }
}
