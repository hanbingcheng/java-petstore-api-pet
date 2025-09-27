package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.common.base.logging.AppLogger;
import com.example.petstore.api.common.base.logging.annotation.StartEndLog;
import com.example.petstore.api.common.base.logging.constant.CommonLogId;
import com.example.petstore.api.common.errorhandler.constant.CommonErrorCode;
import com.example.petstore.api.common.errorhandler.exception.SystemException;
import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.UpdatePetServiceInput;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePetService {

  private final AppLogger logger;
  private final PetRepository petRepository;

  @StartEndLog
  @Transactional
  public void execute(UpdatePetServiceInput input) {

    updatePet(input);
  }

  private void updatePet(UpdatePetServiceInput input) {
    PetEntity pet =
        PetEntity.builder()
            .id(input.getId())
            .name(input.getName())
            .categoryId(input.getCategoryId())
            .status(input.getStatus().getValue())
            .tagIds(input.getTags())
            .photoUrls(input.getPhotoUrls())
            .build();
    try {
      petRepository.update(pet);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "update");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }
}
