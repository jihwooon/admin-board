package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.error.FaqCategoryGroupIdNotFoundException;
import com.example.admin.demo.domain.FaqCategoryGroup;
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

  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> listFaqCategory(Pageable pageable) {
    return FaqCategoryGroupDto.ListFaqCategoryGroupResponse.of(faqCategoryGroupRepository.findAll(pageable));
  }

  /*

  *
  * select * from faqCategoryGroup limit 10;
  * select count(id) from faqCategoryGroup;
  * */

  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategory(final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    FaqCategoryGroup faqCategoryGroup = FaqCategoryGroup.CreateFaqCategoryGroup()
        .faqType(request.getFaqType())
        .title(request.getTitle())
        .build();

    return FaqCategoryGroupDto.CreateFaqCategoryGroupResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
  }

  public void updateFaqCategory(final Long faqCategoryGroupId, final FaqCategoryGroupDto.UpdateFaqCategoryRequest request) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);

    faqCategoryGroup.updateFaqCategory(request);

    FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse.of(faqCategoryGroupRepository.save(faqCategoryGroup));
  }

  /*
  * update faq_category_group set title = :title where id = :id;
  * */

  @Override
  public void deleteFaqCategory(final Long faqCategoryGroupId) {
    FaqCategoryGroup faqCategoryGroup = getFaqCategoryGroupById(faqCategoryGroupId);
    faqCategoryGroupRepository.delete(faqCategoryGroup);
  }

  /*
   * delete from faq_category_group where id = :id;
   * */

  public FaqCategoryGroup getFaqCategoryGroupById(final Long faqCategoryGroupId) {
    return faqCategoryGroupRepository.findById(faqCategoryGroupId)
        .orElseThrow(() -> new FaqCategoryGroupIdNotFoundException(faqCategoryGroupId));
  }
}
