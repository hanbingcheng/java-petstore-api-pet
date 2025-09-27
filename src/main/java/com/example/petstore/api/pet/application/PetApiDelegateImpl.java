package com.example.petstore.api.pet.application;

import com.example.petstore.api.pet.application.mapper.*;
import com.example.petstore.api.pet.domain.service.*;
import com.example.petstore.api.pet.domain.service.dto.*;
import com.example.petstore.api.pet.oas.api.PetApiDelegate;
import com.example.petstore.api.pet.oas.model.*;
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

  private final AddPetMapper addPetMapper;
  private final AddPetService addPetService;

  private final UpdatePetMapper updatePetMapper;
  private final UpdatePetService updatePetService;

  private final DeletePetService deletePetService;

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

  @Override
  public ResponseEntity<AddPetResponseBody> addPet(AddPetRequestBody addPetRequestBody) {
    AddPetServiceInput input = addPetMapper.map(addPetRequestBody);
    AddPetServiceOutput output = addPetService.execute(input);
    return new ResponseEntity<>(addPetMapper.map(output), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updatePet(Long id, UpdatePetRequestBody updatePetRequestBody) {
    UpdatePetServiceInput input = updatePetMapper.map(id, updatePetRequestBody);
    updatePetService.execute(input);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deletePet(Long petId) {
    deletePetService.execute(petId);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
