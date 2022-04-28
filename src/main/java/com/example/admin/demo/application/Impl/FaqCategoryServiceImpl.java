package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.application.error.FaqCategoryGroupIdNotFoundException;
import com.example.admin.demo.application.error.FaqCategoryNotFoundException;
import com.example.admin.demo.domain.FaqCategory;
import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryDto;
import com.example.admin.demo.repository.FaqCategoryGroupRepository;
import com.example.admin.demo.repository.FaqCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqCategoryServiceImpl implements FaqCategoryService {

  private final FaqCategoryRepository faqCategoryRepository;
  private final FaqCategoryGroupService faqCategoryGroupService;
  private final FaqCategoryGroupRepository faqCategoryGroupRepository;

  public FaqCategoryDto.DetailFaqCategoryResponse detailFaqCategory(final Long faqCategoryGroupId,
                                                                    final Long faqId) {
    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = getFaqCategory(faqId);

    return FaqCategoryDto.DetailFaqCategoryResponse.of(faqCategoryGroup, faqCategory);
  }

  public void createFaqCategory(final Long faqCategoryGroupId,
                                final FaqCategoryDto.CreateFaqCategoryRequest request) {

    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = FaqCategory.builder()
        .faqCategoryGroup(faqCategoryGroup)
        .title(request.getTitle())
        .content(request.getContent())
        .build();

    FaqCategoryDto.CreateFaqCategoryResponse.of(faqCategoryRepository.save(faqCategory));
  }

  public void deleteFaqCategory(final Long faqId) {
    FaqCategory faqCategory = getFaqCategory(faqId);

    faqCategoryRepository.delete(faqCategory);
  }

  @Override
  public FaqCategoryDto.ListFaqCategoryResponsePage listFaqCategory(final Pageable pageable,
                                                                    final FaqCategoryDto.SearchConditionRequestDto request) {

    if (request.getFaqCategoryGroupId() == null) {
      return FaqCategoryDto.ListFaqCategoryResponsePage.of(faqCategoryRepository.findAll(pageable));
    } else {
      FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(request.getFaqCategoryGroupId());
      return FaqCategoryDto.ListFaqCategoryResponsePage.of(faqCategoryRepository.findAllByFaqCategoryGroup(pageable, faqCategoryGroup));
    }
  }

  @Override
  public void updateExposeById(final Long faqCategoryGroupId,
                               final Long faqId,
                               final FaqCategoryDto.UpdateExposeRequest expose) {

    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroup(faqCategoryGroupId);
    FaqCategory faqCategory = getFaqCategory(faqId);

    faqCategory.changeExpose(faqCategoryGroup, expose.isExpose());

    faqCategoryRepository.save(faqCategory);
  }

  public FaqCategory getFaqCategory(final Long faqId) {
    return faqCategoryRepository.findById(faqId)
        .orElseThrow(() -> new FaqCategoryNotFoundException("Id 값을 찾을 수 없습니다."));
  }

  public FaqCategoryGroup getFaqCategoryGroup(final Long faqCategoryGroupId) {
    return faqCategoryGroupRepository.findById(faqCategoryGroupId)
        .orElseThrow(() -> new FaqCategoryGroupIdNotFoundException("Id 값을 찾을 수 없습니다."));
  }
}
