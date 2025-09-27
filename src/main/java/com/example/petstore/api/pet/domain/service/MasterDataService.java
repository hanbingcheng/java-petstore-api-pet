package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.common.base.logging.AppLogger;
import com.example.petstore.api.common.base.logging.constant.CommonLogId;
import com.example.petstore.api.common.errorhandler.constant.CommonErrorCode;
import com.example.petstore.api.common.errorhandler.exception.SystemException;
import com.example.petstore.api.pet.domain.model.PetTagEntity;
import com.example.petstore.api.pet.domain.repository.PetTagRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterDataService {

  private final AppLogger logger;
  private final PetTagRepository petTagRepository;

  @Cacheable("petTags")
  public Map<Integer, String> getAllPetTags() {

    try {
      Map<Integer, String> result = new HashMap<>();
      List<PetTagEntity> entities = petTagRepository.getAll();
      entities.parallelStream()
          .forEach(
              entity -> {
                result.put(entity.getId(), entity.getName());
              });
      return result;
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "findByTags");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }
}
