package com.example.admin.demo.application;

import com.example.admin.demo.dto.FaqCategoryDto;
import org.springframework.data.domain.Pageable;

public interface FaqCategoryService {

  FaqCategoryDto.DetailFaqCategoryResponse getFaqCategory(Long faqCategoryGroupId, Long faqId);

  void createFaqCategory(Long faqCategoryGroupId, FaqCategoryDto.CreateFaqCategoryRequest request);

  void deleteFaqCategoryById(Long faqId);

  FaqCategoryDto.ListFaqCategoryResponsePage getFaqCategories(Pageable pageable, FaqCategoryDto.SearchConditionRequestDto request);

  void updateExposeById(Long faqCategoryGroupId, Long faqId, FaqCategoryDto.UpdateExposeRequest expose);

  void deleteFaqCategories(FaqCategoryDto.DeleteFaqCategoryRequest request);
}
