package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.error.FaqCategoryGroupIdNotFoundException;
import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import com.example.admin.demo.repository.FaqCategoryGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryGroupServiceImpl implements FaqCategoryGroupService {

  private final FaqCategoryGroupRepository faqCategoryGroupRepository;

  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> listFaqCategory() {
    return FaqCategoryGroupDto.ListFaqCategoryGroupResponse.of(faqCategoryGroupRepository.findAll());
  }

  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategory(final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    FaqCategoryGroup faqCategoryGroup = FaqCategoryGroup.CreateFaqCategoryGroup()
        .faqType(request.getFaqType())
        .title(request.getTitle())
        .build();

    return FaqCategoryGroupDto.CreateFaqCategoryGroupResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
  }

  public FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse updateFaqCategory(final Long faqCategoryGroupId, final FaqCategoryGroupDto.UpdateFaqCategoryRequest request) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);

    faqCategoryGroup.updateFaqCategory(request);

    return FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
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
