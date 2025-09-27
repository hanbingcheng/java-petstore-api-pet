package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.dto.AddPetServiceInput;
import com.example.petstore.api.pet.domain.service.dto.AddPetServiceOutput;
import com.example.petstore.api.pet.domain.support.ValidationSupport;
import com.example.petstore.api.pet.oas.model.AddPetRequestBody;
import com.example.petstore.api.pet.oas.model.AddPetResponseBody;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
public class AddPetMapper {

  private final CommonMapper commonMapper;

  private final ValidationSupport validationSupport;

  public AddPetServiceInput map(AddPetRequestBody requestBody) {

    validationSupport.validateTagIds(requestBody.getTags());
    List<String> photoUrls = null;
    if (!CollectionUtils.isEmpty(requestBody.getPhotoUrls())) {
      photoUrls =
          requestBody.getPhotoUrls().stream().map(p -> p.toString()).collect(Collectors.toList());
    }

    return AddPetServiceInput.builder()
        .name(requestBody.getName())
        .categoryId(requestBody.getCategoryId())
        .status(requestBody.getStatus())
        .photoUrls(photoUrls)
        .tags(requestBody.getTags())
        .build();
  }

  public AddPetResponseBody map(AddPetServiceOutput output) {
    AddPetResponseBody body = new AddPetResponseBody();
    body.setId(output.getPetId());
    return body;
  }
}
