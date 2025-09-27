package com.example.petstore.api.pet.domain.model;

import java.util.List;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetEntity {
  private Long id;
  private String name;
  private int categoryId;
  private String categoryName;
  private String status; // available, pending, sold
  private List<Integer> tagIds;
  private List<String> photoUrls;
}
