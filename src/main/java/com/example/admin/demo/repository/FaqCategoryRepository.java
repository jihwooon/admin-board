package com.example.admin.demo.repository;

import com.example.admin.demo.domain.FaqCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> , FaqCategoryRepositoryCustom{

//  List<FaqCategoryDto.ListFaqCategoryResponse> findAll(FaqCategory faqCategory, Pageable pageable);

//  List<FaqCategoryDto.ListFaqCategoryResponse> findAllFaqCategoryBy(FaqCategoryDto.SearchConditionRequestDto request, Pageable pageable);
}
