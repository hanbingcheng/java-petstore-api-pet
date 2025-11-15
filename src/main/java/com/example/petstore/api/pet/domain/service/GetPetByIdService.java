package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.GetPetByIdServiceInput;
import com.example.petstore.api.pet.domain.service.dto.GetPetByIdServiceOutput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPetByIdService {

  private final AppLogger logger;
  private final PetRepository petRepository;
  private MasterDataService masterDataService;

  @StartEndLog
  public GetPetByIdServiceOutput execute(GetPetByIdServiceInput input) {

    PetEntity pet;
    try {
      pet = petRepository.getPetById(input.getPetId());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "getPetById");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return GetPetByIdServiceOutput.builder()
        .pet(pet)
        .petTagMappings(masterDataService.getAllPetTags())
        .build();
  }
}
