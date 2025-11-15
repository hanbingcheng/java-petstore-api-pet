package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.FindPetsServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindPetsServiceOutput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class FindPetsService {

  private final AppLogger logger;
  private final PetRepository petRepository;
  private final MasterDataService masterDataService;

  @StartEndLog
  public FindPetsServiceOutput execute(FindPetsServiceInput input) {
    PageHelper.startPage(input.getPageNum(), input.getPageSize());
    List<PetEntity> pets;
    List<Integer> tagIds = null;
    if (!CollectionUtils.isEmpty(input.getTags())) {
      Map<Integer, String> tags = masterDataService.getAllPetTags();
      tagIds =
          tags.entrySet().stream()
              .filter(e -> input.getTags().contains(e.getValue()))
              .map(e -> e.getKey())
              .toList();
      if (StringUtils.isEmpty(tagIds)) {
        PageInfo<PetEntity> pageInfo = new PageInfo<>(new ArrayList<>());
        return FindPetsServiceOutput.builder().pageInfo(pageInfo).build();
      }
    }

    try {
      pets = petRepository.findPets(input.getStatus().getValue(), tagIds);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "findByStatus");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    PageInfo<PetEntity> pageInfo = new PageInfo<>(pets);
    return FindPetsServiceOutput.builder()
        .pageInfo(pageInfo)
        .petTagMappings(masterDataService.getAllPetTags())
        .build();
  }
}
