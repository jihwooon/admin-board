package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.domain.faqCategory.FaqCategory;
import com.example.admin.demo.domain.faqCategory.FaqCategoryGroup;
import com.example.admin.demo.dto.CommonDto;
import com.example.admin.demo.dto.FaqCategoryDto;
import com.example.admin.demo.error.FaqCategoryNotFoundException;
import com.example.admin.demo.repository.FaqCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryServiceImpl implements FaqCategoryService {

  private final FaqCategoryRepository faqCategoryRepository;
  private final FaqCategoryGroupService faqCategoryGroupService;

  public void createFaqCategory(final Long faqCategoryGroupId,
                                final FaqCategoryDto.CreateFaqCategoryRequest request) {

    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = FaqCategory.builder()
        .faqCategoryGroup(faqCategoryGroup)
        .faqTitle(request.getTitle())
        .replayContent(request.getContent())
        .build();

    FaqCategoryDto.CreateFaqCategoryResponse.of(faqCategoryRepository.save(faqCategory));
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
