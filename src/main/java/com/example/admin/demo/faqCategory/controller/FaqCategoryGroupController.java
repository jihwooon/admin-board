package com.example.admin.demo.faqCategory.controller;

import com.example.admin.demo.faqCategory.application.FaqCategoryGroupService;
import com.example.admin.demo.faqCategory.dto.FaqCategoryGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FaqCategoryGroupController {

  private final FaqCategoryGroupService faqCategoryGroupService;

  @PostMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createFaqCategoryGroup(@RequestBody @Valid final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    return faqCategoryGroupService.createFaqCategoryGroup(request);
  }

  @GetMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.OK)
  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> getFaqCategoryGroups(@PageableDefault(size = 10, page = 0) final Pageable pageable) {
    return faqCategoryGroupService.getFaqCategoryGroups(pageable);
  }

  @PutMapping("/faqsGroup/{faqCategoryGroupId}")
  @ResponseStatus(HttpStatus.OK)
  public void updateFaqCategoryGroup(@PathVariable final Long faqCategoryGroupId,
                                     @RequestBody @Valid final FaqCategoryGroupDto.UpdateFaqCategoryRequest request) {
    faqCategoryGroupService.updateFaqCategoryGroup(faqCategoryGroupId, request);
  }

  @DeleteMapping("/faqsGroup/{faqCategoryGroupId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteFaqCategoryGroup(@PathVariable final Long faqCategoryGroupId) {
    faqCategoryGroupService.deleteFaqCategoryGroup(faqCategoryGroupId);
  }

}
