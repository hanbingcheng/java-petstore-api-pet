package com.example.petstore.api.pet.domain.repository;

import com.example.petstore.api.pet.domain.model.PetEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetRepository {
  List<PetEntity> findByStatus(String status);

  List<PetEntity> findByTags(List<String> tagNames);

  PetEntity getPetById(long petId);
}
