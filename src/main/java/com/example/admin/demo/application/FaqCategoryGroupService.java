package com.example.admin.demo.application;

import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqCategoryGroupService {

  List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> listFaqCategory(Pageable pageable);

  FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategory(FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request);

  void updateFaqCategory(Long faqCategoryGroupId, FaqCategoryGroupDto.UpdateFaqCategoryRequest request);

  FaqCategoryGroup getFaqCategoryGroupById(Long faqCategoryGroupId);

  void deleteFaqCategory(Long faqCategoryGroupId);

}
