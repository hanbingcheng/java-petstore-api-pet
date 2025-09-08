package com.example.petstore.api.pet.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPetByIdServiceInput {
  private long petId;
}
