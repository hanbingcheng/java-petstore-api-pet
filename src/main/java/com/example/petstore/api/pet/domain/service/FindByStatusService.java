package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.common.base.logging.AppLogger;
import com.example.petstore.api.common.base.logging.annotation.StartEndLog;
import com.example.petstore.api.common.base.logging.constant.CommonLogId;
import com.example.petstore.api.common.errorhandler.constant.CommonErrorCode;
import com.example.petstore.api.common.errorhandler.exception.SystemException;
import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByStatusServiceOutput;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindByStatusService {

  private final AppLogger logger;
  private final PetRepository petRepository;

  @StartEndLog
  public FindByStatusServiceOutput execute(FindByStatusServiceInput input) {
    PageHelper.startPage(input.getPageNum(), input.getPageSize());
    List<PetEntity> pets;
    try {
      pets = petRepository.findByStatus(input.getStatus().getValue());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "findByStatus");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    PageInfo<PetEntity> pageInfo = new PageInfo<>(pets);
    return FindByStatusServiceOutput.builder().pageInfo(pageInfo).build();
  }
}
