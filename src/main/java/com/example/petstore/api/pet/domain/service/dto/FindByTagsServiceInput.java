package com.example.petstore.api.pet.domain.service.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByTagsServiceInput {
  private List<String> tags;
  private int pageNum;
  private int pageSize;
}
