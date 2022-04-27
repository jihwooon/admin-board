package com.example.admin.demo.application;

import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqCategoryGroupService {

  List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> listFaqCategory(Pageable pageable);

  FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategory(final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request);

  FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse updateFaqCategory(Long faqCategoryGroupId, FaqCategoryGroupDto.UpdateFaqCategoryRequest request);

  FaqCategoryGroup getFaqCategoryGroupById(final Long faqCategoryGroupId);

  void deleteFaqCategory(Long faqCategoryGroupId);

}
