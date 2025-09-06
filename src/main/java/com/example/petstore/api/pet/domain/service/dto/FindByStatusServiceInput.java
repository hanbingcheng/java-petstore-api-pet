package com.example.petstore.api.pet.domain.service.dto;

import com.example.petstore.api.pet.oas.model.PetStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByStatusServiceInput {
  private PetStatus status;
}
