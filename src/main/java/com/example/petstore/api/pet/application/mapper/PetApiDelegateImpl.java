package com.example.petstore.api.pet.application.mapper;

import com.example.petstore.api.pet.domain.service.FindByStatusService;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceOutput;
import com.example.petstore.api.pet.oas.api.PetApiDelegate;
import com.example.petstore.api.pet.oas.model.FindPetsByStatusResponseBody;
import com.example.petstore.api.pet.oas.model.PetStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetApiDelegateImpl implements PetApiDelegate {

  private final FindByStatusMapper findByStatusMapper;
  private final FindByStatusService findByStatusService;

  @Override
  public ResponseEntity<FindPetsByStatusResponseBody> findPetsByStatus(
      PetStatus status, Integer pageNum, Integer pageSize) {
    FindByStatusServiceInput input = findByStatusMapper.map(status, pageNum, pageSize);
    FindByStatusServiceOutput output = findByStatusService.execute(input);
    return new ResponseEntity<>(findByStatusMapper.map(output), HttpStatus.OK);
  }
}
