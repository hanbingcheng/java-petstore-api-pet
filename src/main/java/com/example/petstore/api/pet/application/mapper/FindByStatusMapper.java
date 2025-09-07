package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceOutput;
import com.example.petstore.api.pet.oas.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FindByStatusMapper {

  public FindByStatusServiceInput map(PetStatus status, Integer pageNum, Integer pageSize) {
    return FindByStatusServiceInput.builder()
        .status(status)
        .pageNum(pageNum)
        .pageSize(pageSize)
        .build();
  }

  public FindPetsByStatusResponseBody map(FindByStatusServiceOutput output) {

    Pager pager = new Pager();
    pager.setIsFirstPage(output.getPageInfo().isIsFirstPage());
    pager.setIsLastPage(output.getPageInfo().isIsLastPage());
    pager.setTotal(output.getPageInfo().getTotal());
    pager.setPageNum(output.getPageInfo().getPageNum());
    pager.setPageSize(output.getPageInfo().getPageSize());
    pager.setSize(output.getPageInfo().getSize());
    pager.setPages(output.getPageInfo().getPages());
    List<Pet> pets = new ArrayList<>();
    output.getPageInfo().getList().stream()
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
    FindPetsByStatusResponseBody responseBody = new FindPetsByStatusResponseBody();
    responseBody.setPager(pager);
    responseBody.setList(pets);
    return responseBody;
  }

  private List<PetTag> buildTags(String tagIdsString, String tagNamesString) {
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
