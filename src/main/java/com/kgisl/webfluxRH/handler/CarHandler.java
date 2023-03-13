package com.kgisl.webfluxRH.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kgisl.webfluxRH.dto.CarDto;
import com.kgisl.webfluxRH.model.Car;
import com.kgisl.webfluxRH.service.CarServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarHandler {

    @Autowired
    private CarServiceImpl carServiceImpl;

    public Mono<ServerResponse> loadCars(ServerRequest request) {
        Flux<CarDto> carList = carServiceImpl.loadAllCars();
        return ServerResponse.ok().body(carList, Car.class);
    }

    public Mono<ServerResponse> findCar(ServerRequest request) {
        String carId = String.valueOf(request.pathVariable("id"));
        // carServiceImpl.loadAllCars().filter(c->c.getId()==carId).take(1).single();
        Mono<CarDto> carMono = carServiceImpl.findById(carId).filter(c -> c.getId() == carId);
        return ServerResponse.ok().body(carMono, Car.class);
    }

    public Mono<ServerResponse> saveCar(ServerRequest request) {
        Mono<Car> carMono = request.bodyToMono(Car.class);
        Mono<String> saveResponse = carMono.map(d -> d.getId() + ":" + d.getModel());
        return ServerResponse.ok().body(saveResponse, String.class);
    }

    public Mono<ServerResponse> deleteCar(ServerRequest request) {
        String carId = String.valueOf(request.pathVariable("id"));
        Mono<Void> carMono = carServiceImpl.delete(carId);
        return ServerResponse.ok().body(carMono, Car.class);
    }

    public Mono<ServerResponse> updateCar(ServerRequest request) {
        String carId = String.valueOf(request.pathVariable("id"));
        Mono<Car> cMono = request.bodyToMono(Car.class);
        Mono<Car> carMono = carServiceImpl.update(cMono, carId);
        return ServerResponse.ok().body(carMono, CarDto.class);
    }
}