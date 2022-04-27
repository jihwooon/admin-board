package com.example.admin.demo.application;

import com.example.admin.demo.dto.FaqCategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FaqCategoryService {

  List<FaqCategoryDto.ListFaqCategoryResponse> listFaqCategory();

  FaqCategoryDto.DetailFaqCategoryResponse detailFaqCategory(Long faqCategoryGroupId, Long faqId);

  FaqCategoryDto.CreateFaqCategoryResponse createFaqCategory(Long faqCategoryGroupId, FaqCategoryDto.CreateFaqCategoryRequest request);

  void deleteFaqCategory(Long faqId);

}
