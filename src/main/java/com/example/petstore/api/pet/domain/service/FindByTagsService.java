package com.example.petstore.api.pet.domain.service;

import com.example.petstore.api.common.base.logging.AppLogger;
import com.example.petstore.api.common.base.logging.annotation.StartEndLog;
import com.example.petstore.api.common.base.logging.constant.CommonLogId;
import com.example.petstore.api.common.errorhandler.constant.CommonErrorCode;
import com.example.petstore.api.common.errorhandler.exception.SystemException;
import com.example.petstore.api.pet.domain.model.PetEntity;
import com.example.petstore.api.pet.domain.repository.PetRepository;
import com.example.petstore.api.pet.domain.service.dto.FindByTagsServiceInput;
import com.example.petstore.api.pet.domain.service.dto.FindByTagsServiceOutput;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class FindByTagsService {

  private final AppLogger logger;
  private final PetRepository petRepository;
  private MasterDataService masterDataService;

  @StartEndLog
  public FindByTagsServiceOutput execute(FindByTagsServiceInput input) {

    Map<Integer, String> tags = masterDataService.getAllPetTags();
    List<Integer> tagIds =
        tags.entrySet().stream()
            .filter(e -> input.getTags().contains(e.getValue()))
            .map(e -> e.getKey())
            .toList();
    if (StringUtils.isEmpty(tagIds)) {
      PageInfo<PetEntity> pageInfo = new PageInfo<>(new ArrayList<>());
      return FindByTagsServiceOutput.builder().pageInfo(pageInfo).build();
    }
    PageHelper.startPage(input.getPageNum(), input.getPageSize());
    List<PetEntity> pets;
    try {
      pets = petRepository.findByTags(tagIds);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "findByTags");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    PageInfo<PetEntity> pageInfo = new PageInfo<>(pets);
    return FindByTagsServiceOutput.builder()
        .pageInfo(pageInfo)
        .petTagMappings(masterDataService.getAllPetTags())
        .build();
  }
}
