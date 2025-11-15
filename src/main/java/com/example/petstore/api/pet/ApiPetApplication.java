package com.example.petstore.api.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@EnableCaching
@SpringBootApplication
// @MapperScan("com.example.petstore.api.pet.domain.repository")
public class ApiPetApplication {
  @Autowired private RequestMappingHandlerMapping handlerMapping;

  public static void main(String[] args) {
    SpringApplication.run(ApiPetApplication.class, args);
  }
}
