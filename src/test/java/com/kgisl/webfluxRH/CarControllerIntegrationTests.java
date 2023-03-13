package com.kgisl.webfluxRH;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kgisl.webfluxRH.dto.CarDto;
import com.kgisl.webfluxRH.repository.CarRepository;
import com.kgisl.webfluxRH.service.CarService;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerIntegrationTests {

    @Autowired
    private CarService carService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void before() {
        System.out.println("Before Each Test");
        carRepository.deleteAll().subscribe();
    }

    @Test
    public void testSaveCar() {
        System.out.println("*******Create an Car*******");
        CarDto carDto = new CarDto();
        carDto.setBrand("Test");
        carDto.setModel("Test");
        carDto.setPrice(34567);

        webTestClient.post().uri("/cars/post")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(carDto), CarDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.brand").isEqualTo(carDto.getBrand())
                .jsonPath("$.model").isEqualTo(carDto.getModel())
                .jsonPath("$.price").isEqualTo(carDto.getPrice());
    }

    @Test
    public void testGetSingleCar() {
        System.out.println("*******Get A Car*******");
        CarDto carDto = new CarDto();
        carDto.setBrand("Test");
        carDto.setModel("Test");
        carDto.setPrice(1234567);

        CarDto savedCar = carService.create(carDto).block();

        webTestClient.get().uri("/cars/{id}", Collections.singletonMap("id", savedCar.getId()))
                .exchange()
                .expectStatus().isFound()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.id").isEqualTo(savedCar.getId())
                .jsonPath("$.brand").isEqualTo(carDto.getBrand())
                .jsonPath("$.model").isEqualTo(carDto.getModel())
                .jsonPath("$.price").isEqualTo(carDto.getPrice());
    }

    @Test
    public void testGetAllCars() {
        System.out.println("*******Get All Cars*******");
        CarDto carDto = new CarDto();
        carDto.setBrand("Test");
        carDto.setModel("Test");
        carDto.setPrice(45678);

        carService.create(carDto).block();

        CarDto carDto1 = new CarDto();
        carDto1.setBrand("Test");
        carDto1.setModel("Test");
        carDto1.setPrice(234567);

        carService.create(carDto1).block();

        webTestClient.get().uri("/cars/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CarDto.class)
                .consumeWith(System.out::println);
    }

    @Test
    public void testUpdateCar() {
        System.out.println("*******Update Car*******");
        CarDto carDto = new CarDto();
        carDto.setBrand("Test");
        carDto.setModel("Test");
        carDto.setPrice(234567);

        CarDto savedCar = carService.create(carDto).block();

        CarDto updatedCar = new CarDto();
        updatedCar.setBrand("Test");
        updatedCar.setModel("Test");
        updatedCar.setPrice(98765);

        webTestClient.put().uri("cars/{id}", Collections.singletonMap("id", savedCar.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updatedCar), CarDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.brand").isEqualTo(updatedCar.getBrand())
                .jsonPath("$.model").isEqualTo(updatedCar.getModel())
                .jsonPath("$.price").isEqualTo(updatedCar.getPrice());
    }

    @Test
    public void testDeleteCar() {
        System.out.println("*******Delete Car*******");
        CarDto carDto = new CarDto();
        carDto.setBrand("Test");
        carDto.setModel("Test");
        carDto.setPrice(3456);

        CarDto savedCar = carService.create(carDto).block();

        webTestClient.delete().uri("/cars/{id}", Collections.singletonMap("id", savedCar.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println);
    }
}
