// package com.kgisl.webfluxRH;

// import static org.mockito.Mockito.times;

// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.reactive.server.WebTestClient;
// import org.springframework.web.reactive.function.BodyInserters;

// import com.kgisl.webfluxRH.controller.CarController;
// import com.kgisl.webfluxRH.model.Car;
// import com.kgisl.webfluxRH.repository.CarRepository;
// import reactor.core.publisher.Mono;

// @SpringBootTest
// @WebFluxTest(controllers = CarController.class)
// class WebfluxRhApplicationTests {

// 	@MockBean
//   CarRepository repository;
 
//   @Autowired
//   private WebTestClient webClient;
 
//   @Test
//   void testCreateCar() {
//     Car car = new Car();
//     car.setId(1);
// 	car.setBrand("Test");
//     car.setModel("Test");
//     car.setPrice(1000);
 
//     Mockito.when(repository.save(car)).thenReturn(Mono.just(car));
 
//     webClient.post()
//       .uri("/post")
//       .contentType(MediaType.APPLICATION_JSON)
//       .body(BodyInserters.fromValue(car))
//       .exchange()
//       .expectStatus().isCreated();
 
//     Mockito.verify(repository, times(1)).save(car);
//   }
   
// //   @Test
// //     void testGetCarsByModel() 
// //   {
// //     Car car = new Car();
// //     car.setId(1);
// //     car.setModel("Test");
// //     car.setPrice(1000);
     
// //     List<Car> list = new ArrayList<Car>();
// //     list.add(car);
     
// //     Flux<Car> carFlux = Flux.fromIterable(list);
     
// //         Mockito
// //             .when(repository.findByModel("Test"))
// //             .thenReturn(carFlux);
 
// //         webClient.get().uri("/model/{model}", "Test")
// //           .header(HttpHeaders.ACCEPT, "application/json")
// //           .exchange()
// //           .expectStatus().isOk()
// //           .expectBodyList(Car.class);
         
// //         Mockito.verify(repository, times(1)).findByModel("Test");
// //     }
   
// //   @Test
// //     void testGetCarById() 
// //   {
// //     Car car = new Car();
// //     car.setId(100);
// // 	car.setBrand("Test");
// //     car.setModel("Test");
// //     car.setPrice(1000);
       
// //         Mockito
// //             .when(repository.findById(100))
// //             .thenReturn(Mono.just(car));
 
// //         webClient.get().uri("/{id}", 100)
// //           .exchange()
// //           .expectStatus().isOk()
// //           .expectBody()
// //           .jsonPath("$.model").isNotEmpty()
// //           .jsonPath("$.id").isEqualTo(100)
// //           .jsonPath("$.model").isEqualTo("Test")
// // 		  .jsonPath("$.brand").isEqualTo("Test")
// //           .jsonPath("$.price").isEqualTo(1000);
         
// //         Mockito.verify(repository, times(1)).findById(100);
// //     }
 
// //   @Test
// //     void testDeleteCar() 
// //   {
// //     Mono<Void> voidReturn  = Mono.empty();
// //         Mockito
// //             .when(repository.deleteById(1))
// //             .thenReturn(voidReturn);
 
// //         webClient.get().uri("/delete/{id}", 1)
// //           .exchange()
// //           .expectStatus().isOk();
// //     }
// }
