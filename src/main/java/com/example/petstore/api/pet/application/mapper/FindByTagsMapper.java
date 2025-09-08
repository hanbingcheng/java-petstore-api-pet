package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.dto.FindByTagsServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByTagsServiceOutput;
import com.example.petstore.api.pet.oas.model.*;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindByTagsMapper {

  private final CommonMapper commonMapper;

  public FindByTagsServiceInput map(List<String> tags, Integer pageNum, Integer pageSize) {
    return FindByTagsServiceInput.builder().tags(tags).pageNum(pageNum).pageSize(pageSize).build();
  }

  public FindPetsByTagsResponseBody map(FindByTagsServiceOutput output) {

    FindPetsByTagsResponseBody responseBody = new FindPetsByTagsResponseBody();
    responseBody.setPager(commonMapper.mapPager(output.getPageInfo()));
    responseBody.setList(commonMapper.mapPets(output.getPageInfo()));
    return responseBody;
  }
}
