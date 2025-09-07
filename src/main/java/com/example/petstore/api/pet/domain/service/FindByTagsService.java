package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.common.errorhandler.constant.CommonErrorCode;
import com.example.petstore.api.common.errorhandler.exception.SystemException;
import com.example.petstore.api.common.logging.AppLogger;
import com.example.petstore.api.common.logging.annotation.StartEndLog;
import com.example.petstore.api.common.logging.constant.CommonLogId;
import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.FindByTagsServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByTagsServiceOutput;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindByTagsService {

  private final AppLogger logger;
  private final PetRepository petRepository;

  @StartEndLog
  public FindByTagsServiceOutput execute(FindByTagsServiceInput input) {
    PageHelper.startPage(input.getPageNum(), input.getPageSize());
    List<PetEntity> pets;
    try {
      pets = petRepository.findByTags(input.getTags());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "findByTags");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    PageInfo<PetEntity> pageInfo = new PageInfo<>(pets);
    return FindByTagsServiceOutput.builder().pageInfo(pageInfo).build();
  }
}
