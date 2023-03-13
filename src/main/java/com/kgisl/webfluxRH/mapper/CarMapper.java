package com.kgisl.webfluxRH.mapper;

import com.kgisl.webfluxRH.dto.CarDto;
import com.kgisl.webfluxRH.model.Car;

public class CarMapper {
    public static CarDto mapToCarDto(Car car){
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPrice()
        );
    }

    public static Car mapToCar(CarDto carDto){
        return new Car(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getPrice()
        );
    }
}
