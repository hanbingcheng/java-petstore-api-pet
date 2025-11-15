package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.AddPetServiceInput;
import com.example.petstore.api.pet.domain.service.dto.AddPetServiceOutput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddPetService {

  private final AppLogger logger;
  private final PetRepository petRepository;

  @StartEndLog
  @Transactional
  public AddPetServiceOutput execute(AddPetServiceInput input) {

    long id = insertPet(input);
    return AddPetServiceOutput.builder().petId(id).build();
  }

  private long insertPet(AddPetServiceInput input) {
    PetEntity pet =
        PetEntity.builder()
            .name(input.getName())
            .categoryId(input.getCategoryId())
            .status(input.getStatus().getValue())
            .tagIds(input.getTags())
            .photoUrls(input.getPhotoUrls())
            .build();
    try {
      petRepository.insert(pet);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "insert");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return pet.getId();
  }
}
