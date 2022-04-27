package com.example.admin.demo.repository;

import com.example.admin.demo.dto.FaqCategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqCategoryRepositoryCustom {

  List<FaqCategoryDto.ListFaqCategoryResponse> findAllFaqCategoryBy(FaqCategoryDto.SearchConditionRequestDto request, Pageable pageable);
}
