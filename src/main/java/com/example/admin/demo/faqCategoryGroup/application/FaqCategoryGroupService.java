package com.example.admin.demo.faqCategoryGroup.application;

import com.example.admin.demo.faqCategoryGroup.domain.FaqCategoryGroup;
import com.example.admin.demo.faqCategoryGroup.dto.FaqCategoryGroupDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqCategoryGroupService {

  FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategoryGroup(FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request);

  List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> getFaqCategoryGroups(Pageable pageable);

  void updateFaqCategoryGroup(Long faqCategoryGroupId,
                              FaqCategoryGroupDto.UpdateFaqCategoryRequest request);

  FaqCategoryGroup getFaqCategoryGroupById(Long faqCategoryGroupId);

  void deleteFaqCategoryGroup(Long faqCategoryGroupId);

}
