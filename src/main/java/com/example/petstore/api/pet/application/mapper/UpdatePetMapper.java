package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.dto.UpdatePetServiceInput;
import com.example.petstore.api.pet.domain.support.ValidationSupport;
import com.example.petstore.api.pet.oas.model.UpdatePetRequestBody;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
public class UpdatePetMapper {

  private final CommonMapper commonMapper;

  private final ValidationSupport validationSupport;

  public UpdatePetServiceInput map(Long petId, UpdatePetRequestBody requestBody) {

    validationSupport.validateTagIds(requestBody.getTags());
    List<String> photoUrls = null;
    if (!CollectionUtils.isEmpty(requestBody.getPhotoUrls())) {
      photoUrls =
          requestBody.getPhotoUrls().stream().map(p -> p.toString()).collect(Collectors.toList());
    }

    return UpdatePetServiceInput.builder()
        .id(petId)
        .name(requestBody.getName())
        .categoryId(requestBody.getCategoryId())
        .status(requestBody.getStatus())
        .photoUrls(photoUrls)
        .tags(requestBody.getTags())
        .build();
  }
}
