package com.kgisl.webfluxRH.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.kgisl.webfluxRH.dto.CarDto;
import com.kgisl.webfluxRH.mapper.CarMapper;
import com.kgisl.webfluxRH.model.Car;
import com.kgisl.webfluxRH.repository.CarRepository;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    public CarRepository carRepository;

    @Override
    public Mono<CarDto> create(CarDto carDto) {
        Car car = CarMapper.mapToCar(carDto);
        Mono<Car> savedCar = carRepository.save(car);
        return savedCar
                .map((carEntity) -> CarMapper.mapToCarDto(carEntity));
    }

    @Override
    public Mono<CarDto> findById(String carId) {
        Mono<Car> carMono = carRepository.findById(carId);
        return carMono.map((car -> CarMapper.mapToCarDto(car)));
    }

    @Override
    public Flux<CarDto> loadAllCars() {

        Flux<Car> carFlux = carRepository.findAll();
        return carFlux
                .map((car) -> CarMapper.mapToCarDto(car))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<CarDto> update(CarDto carDto, String carId) {

        Mono<Car> carMono = carRepository.findById(carId);

        return carMono.flatMap((existingCar) -> {
            existingCar.setBrand(carDto.getBrand());
            existingCar.setModel(carDto.getModel());
            existingCar.setPrice(carDto.getPrice());
            return carRepository.save(existingCar);
        }).map((car -> CarMapper.mapToCarDto(car)));
    }

    @Override
    public Mono<Void> delete(String carId) {
        return carRepository.deleteById(carId);
    }

    public Mono<Car> update(Mono<Car> car, String carId) {
        return carRepository.save(car);
    }
}