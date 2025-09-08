package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.service.dto.GetPetByIdServiceInput;
import com.example.petstore.api.pet.domain.service.dto.GetPetByIdServiceOutput;
import com.example.petstore.api.pet.oas.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetPetByIdMapper {

  private final CommonMapper commonMapper;

  public GetPetByIdServiceInput map(long petId) {
    return GetPetByIdServiceInput.builder().petId(petId).build();
  }

  public Pet map(GetPetByIdServiceOutput output) {

    PetEntity p = output.getPet();
    Pet pet = new Pet();
    pet.setId(p.getId());
    pet.setName(p.getName());
    PetCategory category = new PetCategory();
    category.setId(p.getCategoryId());
    category.setName(p.getCategoryName());
    pet.setCategory(category);
    pet.setTags(commonMapper.buildTags(p.getTagIdsString(), p.getTagNamesString()));
    pet.setPhotoUrls(commonMapper.buildPhotoUrls(p.getPhotoUrlsString()));
    pet.setStatus(PetStatus.fromValue(p.getStatus()));

    return pet;
  }
}
