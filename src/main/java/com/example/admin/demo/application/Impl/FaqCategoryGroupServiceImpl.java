package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.error.FaqCategoryGroupIdNotFoundException;
import com.example.admin.demo.domain.faqCategory.FaqCategory;
import com.example.admin.demo.domain.faqCategory.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import com.example.admin.demo.repository.FaqCategoryGroupRepository;
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

  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategoryGroup(final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    FaqCategoryGroup faqCategoryGroup = FaqCategoryGroup.CreateFaqCategoryGroup()
        .faqType(request.getFaqType())
        .title(request.getTitle())
        .build();

    return FaqCategoryGroupDto.CreateFaqCategoryGroupResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
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
