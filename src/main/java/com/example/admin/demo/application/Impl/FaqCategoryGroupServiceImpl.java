package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryDto;
import com.example.admin.demo.repository.FaqCategoryGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryGroupServiceImpl implements FaqCategoryGroupService {

  private final FaqCategoryGroupRepository faqCategoryGroupRepository;

  public List<FaqCategoryDto.ListFaqCategoryResponse> listFaqCategory() {
    return FaqCategoryDto.ListFaqCategoryResponse.of(faqCategoryGroupRepository.findAll());
  }

  public FaqCategoryDto.CreateFaqCategoryResponse createFaqCategory(final FaqCategoryDto.CreateFaqCategoryRequest request) {
    FaqCategoryGroup faqCategoryGroup = FaqCategoryGroup.builder()
        .title(request.getTitle())
        .build();
    return FaqCategoryDto.CreateFaqCategoryResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
  }
}
