package com.kgisl.webfluxRH.service;

import com.kgisl.webfluxRH.dto.CarDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {

    Mono<CarDto> create(CarDto e);

    Mono<CarDto> findById(String id);

    Flux<CarDto> loadAllCars();

    Mono<CarDto> update(CarDto car, String id);

    Mono<Void> delete(String id);
    
}

