package com.example.petstore.api.pet.domain.support;

import com.example.petstore.api.pet.domain.service.MasterDataService;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.api.errorhandler.exception.AppValidationException;
import com.example.petstore.common.core.base.logging.AppLogger;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
public class ValidationSupport {

  private AppLogger logger;
  private final MasterDataService masterDataService;

  /**
   * ペットのタグ名をチェックする
   *
   * @param tagNames　入力されたタグ名のリスト
   */
  public void validateTagNames(List<String> tagNames) {
    List<String> invalidTagNames = findInvalidTagNames(tagNames);
    if (!CollectionUtils.isEmpty(invalidTagNames)) {
      throw new AppValidationException(
          CommonErrorCode.REQUEST_PARAMETER_ERROR, "tags", invalidTagNames, "invalid tag names");
    }
  }

  /**
   * タグIDをチェックする
   *
   * @param tagIds 入力されたタグIDのリスト
   */
  public void validateTagIds(List<Integer> tagIds) {
    List<Integer> invalidTagIds = findInvalidTagIdss(tagIds);
    if (!CollectionUtils.isEmpty(invalidTagIds)) {
      throw new AppValidationException(
          CommonErrorCode.REQUEST_PARAMETER_ERROR, "tags", invalidTagIds, "invalid tag Ids");
    }
  }

  private List<Integer> findInvalidTagIdss(List<Integer> tagIds) {
    Collection<Integer> validTagIds = masterDataService.getAllPetTags().keySet();
    if (CollectionUtils.isEmpty(validTagIds)) {
      return null;
    }
    return tagIds.stream().filter(t -> !validTagIds.contains(t)).toList();
  }

  private List<String> findInvalidTagNames(List<String> tagNames) {
    Collection<String> validTagNames = masterDataService.getAllPetTags().values();
    if (CollectionUtils.isEmpty(tagNames)) {
      return null;
    }
    return tagNames.stream().filter(t -> !validTagNames.contains(t)).toList();
  }
}
