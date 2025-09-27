package com.example.petstore.api.pet.domain.service.dto;

import com.example.petstore.api.pet.oas.model.PetStatus;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPetServiceInput {
  private String name;
  private Integer categoryId;
  private List<Integer> tags;
  private List<String> photoUrls;
  private PetStatus status;
}
