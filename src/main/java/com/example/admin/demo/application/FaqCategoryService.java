package com.example.admin.demo.application;

import com.example.admin.demo.dto.FaqCategoryDto;
import org.springframework.data.domain.Pageable;

public interface FaqCategoryService {

  FaqCategoryDto.DetailFaqCategoryResponse detailFaqCategory(Long faqCategoryGroupId, Long faqId);

  void createFaqCategory(Long faqCategoryGroupId, FaqCategoryDto.CreateFaqCategoryRequest request);

  void deleteFaqCategory(Long faqId);

  FaqCategoryDto.ListFaqCategoryResponsePage listFaqCategory(Pageable pageable, FaqCategoryDto.SearchConditionRequestDto request);

  void updateExposeById(Long faqCategoryGroupId, Long faqId, FaqCategoryDto.UpdateExposeRequest expose);
}
