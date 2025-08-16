package com.example.petstore.api.pet.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetEntity {
  private Long id;
  private String name;
  private CategoryEntity category;
  private List<String> photoUrls;
  private List<TagEntity> tags;
  private String status; // available, pending, sold
}
