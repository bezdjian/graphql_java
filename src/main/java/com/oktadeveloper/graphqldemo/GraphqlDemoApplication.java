package com.oktadeveloper.graphqldemo;

import com.oktadeveloper.graphqldemo.entity.Food;
import com.oktadeveloper.graphqldemo.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class GraphqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlDemoApplication.class, args);
    }

    // Initialize sample data
    @Bean
    ApplicationRunner init(FoodService foodService) {
        return args -> {
            Stream.of("Pizza", "Spam", "Eggs", "Avocado").forEach(name -> {
                foodService.saveFood(
                        Food.builder()
                                .name(name)
                                .build());
            });
            foodService.getFoods().forEach(f -> log.info(f.toString()));
        };
    }
}