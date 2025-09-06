package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.model.TagEntity;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceOutput;
import com.example.petstore.api.pet.oas.model.Pet;
import com.example.petstore.api.pet.oas.model.PetCategory;
import com.example.petstore.api.pet.oas.model.PetStatus;
import com.example.petstore.api.pet.oas.model.PetTag;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FindByStatusMapper {

  public FindByStatusServiceInput map(PetStatus status) {
    return FindByStatusServiceInput.builder().status(status).build();
  }

  public List<Pet> map(FindByStatusServiceOutput output) {

    List<Pet> pets = new ArrayList<>();
    output.getPets().stream()
        .forEach(
            p -> {
              Pet pet = new Pet();
              pet.setId(p.getId());
              pet.setName(p.getName());
              PetCategory category = new PetCategory();
              category.setId(p.getCategory().getId());
              category.setName(p.getCategory().getName());
              pet.setCategory(category);
              pet.setTags(convertTags(p.getTags()));
              pet.setPhotoUrls(p.getPhotoUrls());
              pet.setStatus(PetStatus.fromValue(p.getStatus()));

              pets.add(pet);
            });
    return pets;
  }

  private List<PetTag> convertTags(List<TagEntity> tagEntities) {
    List<PetTag> tags = new ArrayList<>();
    tagEntities.forEach(
        t -> {
          PetTag tag = new PetTag();
          tag.setId(t.getId());
          tag.setName(t.getName());
          tags.add(tag);
        });
    return tags;
  }
}
