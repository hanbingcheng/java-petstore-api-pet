package com.example.petstore.api.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan("com.example.petstore.api.pet.domain.repository")
public class ApiPetApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiPetApplication.class, args);
  }
}
