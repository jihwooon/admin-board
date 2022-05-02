package com.example.admin.demo.application;

import com.example.admin.demo.domain.faqCategory.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
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
