package com.example.admin.demo.application;

import com.example.admin.demo.dto.CommonDto;
import com.example.admin.demo.dto.FaqCategoryDto;
import org.springframework.data.domain.Pageable;

public interface FaqCategoryService {

  FaqCategoryDto.DetailFaqCategoryResponse getFaqCategory(Long faqCategoryGroupId,
                                                          Long faqId);

  void createFaqCategory(Long faqCategoryGroupId,
                         FaqCategoryDto.CreateFaqCategoryRequest request);

  CommonDto.PageResponse getFaqCategories(Pageable pageable,
                                          FaqCategoryDto.SearchRequest request);

  void updateExposeById(Long faqId,
                        FaqCategoryDto.UpdateExposeRequest expose);

  void deleteById(Long faqId);

  void deleteFaqCategories(FaqCategoryDto.DeleteFaqCategoryRequest request);
}
