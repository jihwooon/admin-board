package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.dto.FaqCategoryDto;
import com.example.admin.demo.repository.FaqCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryServiceImpl implements FaqCategoryService {

  private final FaqCategoryRepository faqCategoryRepository;

  public List<FaqCategoryDto.ListFaqCategoryResponse> listFaqCategory() {
    return FaqCategoryDto.ListFaqCategoryResponse.of(faqCategoryRepository.findAll());
  }

}
