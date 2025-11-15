package com.example.petstore.api.pet.domain.service.dto;

import com.example.petstore.api.pet.oas.model.PetStatus;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPetsServiceInput {
  private PetStatus status;
  private List<String> tags;
  private int pageNum;
  private int pageSize;
}
