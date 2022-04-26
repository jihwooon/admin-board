package com.example.admin.demo.application;

import com.example.admin.demo.dto.FaqCategoryDto;

import java.util.List;

public interface FaqCategoryGroupService {

  List<FaqCategoryDto.ListFaqCategoryResponse> listFaqCategory();

  FaqCategoryDto.CreateFaqCategoryResponse createFaqCategory(final FaqCategoryDto.CreateFaqCategoryRequest request);
}
