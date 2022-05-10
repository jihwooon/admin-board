package com.example.admin.demo.faqCategory.repository;

import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaqCategoryRepositoryCustom {

  Page<FaqCategoryDto.SearchResultResponse> getFaqCategoryByCondition(Pageable pageable,
                                                                      FaqCategoryDto.SearchRequest request);

}
