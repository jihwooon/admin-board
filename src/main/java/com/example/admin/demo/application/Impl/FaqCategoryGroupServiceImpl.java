package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.error.FaqCategoryGroupIdNotFoundException;
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

  public FaqCategoryDto.UpdateFaqCategoryResponse updateFaqCategory(Long faqCategoryGroupId, FaqCategoryDto.UpdateFaqCategoryRequest request) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);

    faqCategoryGroup.updateFaqCategory(request);

    return FaqCategoryDto.UpdateFaqCategoryResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
  }

  @Override
  public void deleteFaqCategory(final Long faqCategoryGroupId) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);
    faqCategoryGroupRepository.delete(faqCategoryGroup);
  }

  public FaqCategoryGroup getFaqCategoryGroupById(final Long faqCategoryGroupId) {
    return faqCategoryGroupRepository.findById(faqCategoryGroupId)
        .orElseThrow(() -> new FaqCategoryGroupIdNotFoundException(faqCategoryGroupId));
  }
}
