package com.kgisl.webfluxRH.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kgisl.webfluxRH.handler.CarHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private CarHandler handler;
    
    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/cars", handler::loadCars)
                .GET("/router/cars/{id}", handler::findCar)
                .POST("/router/cars/post", handler::saveCar)
                .DELETE("/router/cars/delete/{id}", handler::deleteCar)
                .build();
    }
}
