package com.tpe.service;

import com.netflix.discovery.EurekaClient;
import com.tpe.domain.Car;
import com.tpe.dto.AppLogRequest;
import com.tpe.dto.CarRequest;
import com.tpe.enums.AppLogLevel;
import com.tpe.respository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final EurekaClient eurekaClient;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    public void createCar(CarRequest carRequest) {

        Car car = modelMapper.map(carRequest, Car.class);

        carRepository.save(car);


        // LOG ISLEMLERI YAPILACAK

        String baseUrl=eurekaClient
                .getApplication("log-service")
                .getInstances().get(0)//birden fazla ornegi olabilirdi
                .getHomePageUrl();//http://localhost:8083

        String endpoint=baseUrl + "/log";


        //request bodysini olusturalim
        AppLogRequest appLogRequest = new AppLogRequest();
        appLogRequest.setLevel(AppLogLevel.INFO.getName());//INFO
        appLogRequest.setDescription("Car is saved" + car.getId());
        appLogRequest.setTime(LocalDateTime.now());


        //post method ile req gonderelim
        ResponseEntity<String> logResponse = restTemplate.postForEntity(endpoint,appLogRequest,String.class);
        //appLogRequest objesini JSON a donusturup post methodu ile endpointte
        // verilen adrese requesti gonderiyor.


        if(logResponse.getStatusCode()!= HttpStatus.CREATED){
            throw new RuntimeException(("Log couldn't created"));
        }



    }
}
