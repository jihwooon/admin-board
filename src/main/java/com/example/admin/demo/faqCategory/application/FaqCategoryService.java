package com.example.admin.demo.faqCategory.application;

import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import org.springframework.data.domain.Pageable;

public interface FaqCategoryService {

  FaqCategoryDto.DetailFaqCategoryResponse getFaqCategory(Long faqCategoryGroupId,
                                                          Long faqId);

  Long createFaqCategory(Long faqCategoryGroupId,
                         FaqCategoryDto.CreateFaqCategoryRequest request);

  CommonDto.PageResponse getFaqCategories(Pageable pageable,
                                          FaqCategoryDto.SearchRequest request);

  void updateExposeById(Long faqId,
                        FaqCategoryDto.UpdateExposeRequest expose);

  void deleteById(Long faqId);

  void deleteFaqCategories(FaqCategoryDto.DeleteFaqCategoryRequest request);
}
