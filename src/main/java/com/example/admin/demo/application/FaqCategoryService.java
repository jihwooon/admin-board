package com.example.admin.demo.application;

import com.example.admin.demo.domain.FaqCategory;
import com.example.admin.demo.dto.FaqCategoryDto;

import java.util.List;

public interface FaqCategoryService {

  List<FaqCategoryDto.ListFaqCategoryResponse> listFaqCategory();

  FaqCategoryDto.DetailFaqCategoryResponse detailFaqCategory(Long faqId);
}
