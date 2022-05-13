package com.example.admin.demo.faqCategory.application.Impl;

import com.example.admin.demo.faqCategory.application.FaqCategoryGroupService;
import com.example.admin.demo.faqCategory.application.FaqCategoryService;
import com.example.admin.demo.faqCategory.domain.FaqCategory;
import com.example.admin.demo.faqCategory.domain.FaqCategoryGroup;
import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import com.example.admin.demo.common.error.FaqCategoryNotFoundException;
import com.example.admin.demo.faqCategory.repository.FaqCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryServiceImpl implements FaqCategoryService {

  private final FaqCategoryRepository faqCategoryRepository;
  private final FaqCategoryGroupService faqCategoryGroupService;

  public Long createFaqCategory(final Long faqCategoryGroupId,
                                final FaqCategoryDto.CreateFaqCategoryRequest request) {

    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = FaqCategory.builder()
        .faqCategoryGroup(faqCategoryGroup)
        .faqTitle(request.getFaqCategoryTitle())
        .replayContent(request.getFaqCategoryContent())
        .build();

    return faqCategoryRepository.save(faqCategory).getId();
  }

  @Override
  public CommonDto.PageResponse getFaqCategories(final Pageable pageable,
                                                 final FaqCategoryDto.SearchRequest request) {

    return CommonDto.PageResponse.of(faqCategoryRepository.getFaqCategoryByCondition(pageable, request));
  }

  public FaqCategoryDto.DetailFaqCategoryResponse getFaqCategory(final Long faqCategoryGroupId,
                                                                 final Long faqId) {
    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = getFaqCategory(faqId);

    return FaqCategoryDto.DetailFaqCategoryResponse.of(faqCategoryGroup, faqCategory);
  }

  @Override
  public void updateExposeById(final Long faqId,
                               final FaqCategoryDto.UpdateExposeRequest expose) {
    FaqCategory faqCategory = getFaqCategory(faqId);

    faqCategory.changeExpose(expose.getExpose());

    faqCategoryRepository.save(faqCategory);
  }

  public void deleteById(final Long faqId) {
    FaqCategory faqCategory = getFaqCategory(faqId);

    faqCategory.changeEnable(false);

    faqCategoryRepository.save(faqCategory);
  }

  @Override
  public void deleteFaqCategories(final FaqCategoryDto.DeleteFaqCategoryRequest request) {

    List<FaqCategory> faqCategories = faqCategoryRepository.findAllById(request.getFaqCategories());

    for (FaqCategory faqCategory : faqCategories) {
      faqCategory.changeEnable(false);
    }

    faqCategoryRepository.saveAll(faqCategories);
  }

  public FaqCategory getFaqCategory(final Long faqId) {
    return faqCategoryRepository.findById(faqId)
        .orElseThrow(() -> new FaqCategoryNotFoundException("Id 값을 찾을 수 없습니다."));
  }

}
