package com.example.petstore.api.pet.domain.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetEntity {
  private Long id;
  private String name;
  private Long categoryId;
  private String categoryName;
  private String status; // available, pending, sold

  // GROUP_CONCAT結果を保持する一時フィールド
  private String tagIdsString;
  private String tagNamesString;
  private String photoUrlsString;
}
