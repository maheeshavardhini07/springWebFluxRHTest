package com.kgisl.webfluxRH.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.kgisl.webfluxRH.model.Car;

import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveCrudRepository<Car, String> {

    Mono<Car> save(Mono<Car> car);
    
}
