package com.kgisl.webfluxRH.controller;

import com.kgisl.webfluxRH.service.CarServiceImpl;
import com.kgisl.webfluxRH.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<CarDto> getAllCars() {
        System.out.println("Found All Cars");
        return carService.loadAllCars();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<CarDto> getEmployee(@PathVariable("id") String carId) {
        System.out.println("Found Car Id "+carId);
        return carService.findById(carId);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CarDto> create(@RequestBody CarDto carDto) {
        System.out.println("New Car Created");
        return carService.create(carDto);
    }

    @PutMapping("{id}")
    public Mono<CarDto> updateEmployee(@RequestBody CarDto carDto,
            @PathVariable("id") String id) {
                System.out.println("Car Id " + id + " Updated");
        return carService.update(carDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Void>> deleteCarById(@PathVariable String id) {
        System.out.println("Car Id " + id +" Deleted Successfully");
        return carService.delete(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.ok().build());
    }
}
