package com.example.petstore.api.pet.domain.repository;

import com.example.petstore.api.pet.domain.model.PetEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetRepository {
  List<PetEntity> findPets(String status, List<Integer> tagIds);

  PetEntity getPetById(long petId);

  void insert(PetEntity pet);

  void update(PetEntity pet);

  void delete(long id);
}
