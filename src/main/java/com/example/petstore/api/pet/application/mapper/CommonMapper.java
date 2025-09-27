package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.oas.model.*;
import com.github.pagehelper.PageInfo;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommonMapper {

  public Pager mapPager(PageInfo<PetEntity> pageInfo) {

    Pager pager = new Pager();
    pager.setIsFirstPage(pageInfo.isIsFirstPage());
    pager.setIsLastPage(pageInfo.isIsLastPage());
    pager.setTotal(pageInfo.getTotal());
    pager.setPageNum(pageInfo.getPageNum());
    pager.setPageSize(pageInfo.getPageSize());
    pager.setSize(pageInfo.getSize());
    pager.setPages(pageInfo.getPages());

    return pager;
  }

  public List<Pet> mapPets(PageInfo<PetEntity> pageInfo, Map<Integer, String> tagMappings) {

    List<Pet> pets = new ArrayList<>();
    pageInfo.getList().stream()
        .forEach(
            p -> {
              Pet pet = new Pet();
              pet.setId(p.getId());
              pet.setName(p.getName());
              PetCategory category = new PetCategory();
              category.setId(p.getCategoryId());
              category.setName(p.getCategoryName());
              pet.setCategory(category);
              pet.setTags(buildTags(p.getTagIds(), tagMappings));
              pet.setPhotoUrls(p.getPhotoUrls());
              pet.setStatus(PetStatus.fromValue(p.getStatus()));

              pets.add(pet);
            });
    return pets;
  }

  public List<PetTag> buildTags(List<Integer> tagIds, Map<Integer, String> tagMappings) {
    List<PetTag> tags = new ArrayList<>();
    if (tagIds != null && tagMappings != null) {

      for (int id : tagIds) {
        PetTag tag = new PetTag();
        tag.setId(id);
        tag.setName(tagMappings.get(id));
        tags.add(tag);
      }
    }
    return tags;
  }
}
