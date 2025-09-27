package com.example.petstore.api.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
// @MapperScan("com.example.petstore.api.pet.domain.repository")
public class ApiPetApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiPetApplication.class, args);
  }
}
