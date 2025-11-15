package com.example.petstore.api.pet.domain.service.dto;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.github.pagehelper.PageInfo;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPetsServiceOutput {
  private PageInfo<PetEntity> pageInfo;
  private Map<Integer, String> petTagMappings;
}
