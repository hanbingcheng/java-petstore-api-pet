package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.common.base.logging.AppLogger;
import com.example.petstore.api.common.base.logging.annotation.StartEndLog;
import com.example.petstore.api.common.base.logging.constant.CommonLogId;
import com.example.petstore.api.common.errorhandler.constant.CommonErrorCode;
import com.example.petstore.api.common.errorhandler.exception.SystemException;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePetService {

  private final AppLogger logger;
  private final PetRepository petRepository;

  @StartEndLog
  @Transactional
  public void execute(long petId) {

    deletePet(petId);
  }

  private void deletePet(long petId) {
    try {
      petRepository.delete(petId);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "delete");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }
}
