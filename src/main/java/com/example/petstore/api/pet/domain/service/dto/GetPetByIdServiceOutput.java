package com.example.petstore.api.pet.domain.service.dto;

import com.example.petstore.api.pet.domain.model.PetEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPetByIdServiceOutput {
  private PetEntity pet;
}
