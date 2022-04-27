package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.application.error.FaqCategoryNotFoundException;
import com.example.admin.demo.domain.FaqCategory;
import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryDto;
import com.example.admin.demo.repository.FaqCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryServiceImpl implements FaqCategoryService {

  private final FaqCategoryRepository faqCategoryRepository;
  private final FaqCategoryGroupService faqCategoryGroupService;

  public List<FaqCategoryDto.ListFaqCategoryResponse> listFaqCategory() {
    return FaqCategoryDto.ListFaqCategoryResponse.of(faqCategoryRepository.findAll());
  }

  public FaqCategoryDto.DetailFaqCategoryResponse detailFaqCategory(final Long faqCategoryGroupId, final Long faqId) {
    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = getFaqCategory(faqId);

    return FaqCategoryDto.DetailFaqCategoryResponse.of(faqCategoryGroup, faqCategory);
  }

  public FaqCategoryDto.CreateFaqCategoryResponse createFaqCategory(final Long faqCategoryGroupId, final FaqCategoryDto.CreateFaqCategoryRequest request) {
    FaqCategoryGroup faqCategoryGroup = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);
    FaqCategory faqCategory = FaqCategory.builder()
        .faqCategoryGroup(faqCategoryGroup)
        .title(request.getTitle())
        .content(request.getContent())
        .build();

    return FaqCategoryDto.CreateFaqCategoryResponse.of(faqCategoryRepository.save(faqCategory));
  }

  public void deleteFaqCategory(Long faqId) {
    FaqCategory faqCategory = getFaqCategory(faqId);

    faqCategoryRepository.delete(faqCategory);
  }


  public FaqCategory getFaqCategory(final Long faqId) {
    return faqCategoryRepository.findById(faqId)
        .orElseThrow(() -> new FaqCategoryNotFoundException(faqId));
  }

}
