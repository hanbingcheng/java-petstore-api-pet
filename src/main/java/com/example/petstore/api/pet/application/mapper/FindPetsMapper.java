package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.dto.FindPetsServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindPetsServiceOutput;
import com.example.petstore.api.pet.oas.model.*;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindPetsMapper {

  private final CommonMapper commonMapper;

  public FindPetsServiceInput map(
      PetStatus status, List<String> tags, Integer pageNum, Integer pageSize) {
    return FindPetsServiceInput.builder()
        .status(status)
        .tags(tags)
        .pageNum(pageNum)
        .pageSize(pageSize)
        .build();
  }

  public FindPetsResponseBody map(FindPetsServiceOutput output) {

    FindPetsResponseBody responseBody = new FindPetsResponseBody();
    responseBody.setPager(commonMapper.mapPager(output.getPageInfo()));
    responseBody.setList(commonMapper.mapPets(output.getPageInfo(), output.getPetTagMappings()));
    return responseBody;
  }
}
