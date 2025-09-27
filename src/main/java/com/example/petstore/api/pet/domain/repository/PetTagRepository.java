package com.example.petstore.api.pet.domain.repository;

import com.example.petstore.api.pet.domain.model.PetTagEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetTagRepository {

  List<PetTagEntity> getAll();
}
