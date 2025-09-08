package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.oas.model.*;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

  public List<Pet> mapPets(PageInfo<PetEntity> pageInfo) {

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
              pet.setTags(buildTags(p.getTagIdsString(), p.getTagNamesString()));
              pet.setPhotoUrls(buildPhotoUrls(p.getPhotoUrlsString()));
              pet.setStatus(PetStatus.fromValue(p.getStatus()));

              pets.add(pet);
            });
    return pets;
  }

  public List<PetTag> buildTags(String tagIdsString, String tagNamesString) {
    List<PetTag> tags = new ArrayList<>();
    if (tagIdsString != null && tagNamesString != null) {
      String[] idArray = tagIdsString.split(",");
      String[] nameArray = tagNamesString.split(",");

      int length = Math.min(idArray.length, nameArray.length);

      for (int i = 0; i < length; i++) {
        PetTag tag = new PetTag();
        try {
          tag.setId(Long.parseLong(idArray[i].trim()));
        } catch (NumberFormatException e) {
          continue;
        }
        tag.setName(nameArray[i].trim());
        tags.add(tag);
      }
    }
    return tags;
  }

  public List<String> buildPhotoUrls(String photoUrlsString) {
    List<String> photoUrls;
    if (Objects.isNull(photoUrlsString)) {
      return new ArrayList<>();
    }
    return Arrays.stream(photoUrlsString.split(",")).map(String::trim).collect(Collectors.toList());
  }
}
