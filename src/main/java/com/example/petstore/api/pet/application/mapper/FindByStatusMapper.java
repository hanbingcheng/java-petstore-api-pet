package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceOutput;
import com.example.petstore.api.pet.oas.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindByStatusMapper {

  private final CommonMapper commonMapper;

  public FindByStatusServiceInput map(PetStatus status, Integer pageNum, Integer pageSize) {
    return FindByStatusServiceInput.builder()
        .status(status)
        .pageNum(pageNum)
        .pageSize(pageSize)
        .build();
  }

  public FindPetsByStatusResponseBody map(FindByStatusServiceOutput output) {

    FindPetsByStatusResponseBody responseBody = new FindPetsByStatusResponseBody();
    responseBody.setPager(commonMapper.mapPager(output.getPageInfo()));
    responseBody.setList(commonMapper.mapPets(output.getPageInfo()));
    return responseBody;
  }
}
