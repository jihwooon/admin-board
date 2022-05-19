package com.example.admin.demo.faqCategory.application.Impl;

import com.example.admin.demo.faqCategory.application.FaqCategoryGroupService;
import com.example.admin.demo.common.error.FaqCategoryGroupIdNotFoundException;
import com.example.admin.demo.faqCategory.domain.FaqCategory;
import com.example.admin.demo.faqCategory.domain.FaqCategoryGroup;
import com.example.admin.demo.faqCategory.dto.FaqCategoryGroupDto;
import com.example.admin.demo.faqCategory.repository.FaqCategoryGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqCategoryGroupServiceImpl implements FaqCategoryGroupService {

  private final FaqCategoryGroupRepository faqCategoryGroupRepository;

  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> getFaqCategoryGroups(final Pageable pageable) {
    return FaqCategoryGroupDto.ListFaqCategoryGroupResponse.of(faqCategoryGroupRepository.findAll(pageable));
  }

  public Long createFaqCategoryGroup(final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    FaqCategoryGroup faqCategoryGroup = FaqCategoryGroup.builder()
        .faqType(request.getFaqType())
        .title(request.getFaqCategoryGroupTitle())
        .build();

    return faqCategoryGroupRepository.save(faqCategoryGroup).getId();
  }

  public void updateFaqCategoryGroup(final Long faqCategoryGroupId,
                                     final FaqCategoryGroupDto.UpdateFaqCategoryRequest request) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);

    faqCategoryGroup.updateFaqCategory(request);

    FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
  }

  @Override
  public void deleteFaqCategoryGroup(final Long faqCategoryGroupId) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);

    faqCategoryGroup.changeEnable(false);

    for (FaqCategory faqCategory : faqCategoryGroup.getFaqCategories()) {
      faqCategory.changeEnable(false);
    }

    faqCategoryGroupRepository.save(faqCategoryGroup);
  }

  public FaqCategoryGroup getFaqCategoryGroupById(final Long faqCategoryGroupId) {
    return faqCategoryGroupRepository.findById(faqCategoryGroupId)
        .orElseThrow(() -> new FaqCategoryGroupIdNotFoundException("Id를 찾을 수 없습니다."));
  }
}
