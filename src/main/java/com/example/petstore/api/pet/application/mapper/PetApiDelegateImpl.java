package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.FindByStatusService;
import com.example.petstore.api.pet.domain.service.FindByTagsService;
import com.example.petstore.api.pet.domain.service.GetPetByIdService;
import com.example.petstore.api.pet.domain.service.dto.*;
import com.example.petstore.api.pet.oas.api.PetApiDelegate;
import com.example.petstore.api.pet.oas.model.FindPetsByStatusResponseBody;
import com.example.petstore.api.pet.oas.model.FindPetsByTagsResponseBody;
import com.example.petstore.api.pet.oas.model.Pet;
import com.example.petstore.api.pet.oas.model.PetStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetApiDelegateImpl implements PetApiDelegate {

  private final FindByStatusMapper findByStatusMapper;
  private final FindByStatusService findByStatusService;

  private final FindByTagsMapper findBytagsMapper;
  private final FindByTagsService findByTagsService;

  private final GetPetByIdMapper getPetByIdMapper;
  private final GetPetByIdService getPetByIdService;

  @Override
  public ResponseEntity<FindPetsByStatusResponseBody> findPetsByStatus(
      PetStatus status, Integer pageNum, Integer pageSize) {
    FindByStatusServiceInput input = findByStatusMapper.map(status, pageNum, pageSize);
    FindByStatusServiceOutput output = findByStatusService.execute(input);
    return new ResponseEntity<>(findByStatusMapper.map(output), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FindPetsByTagsResponseBody> findPetsByTags(
      List<String> tags, Integer pageNum, Integer pageSize) {
    FindByTagsServiceInput input = findBytagsMapper.map(tags, pageNum, pageSize);
    FindByTagsServiceOutput output = findByTagsService.execute(input);
    return new ResponseEntity<>(findBytagsMapper.map(output), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Pet> getPetById(Long petId) {
    GetPetByIdServiceInput input = getPetByIdMapper.map(petId);
    GetPetByIdServiceOutput output = getPetByIdService.execute(input);
    return new ResponseEntity<>(getPetByIdMapper.map(output), HttpStatus.OK);
  }
}
