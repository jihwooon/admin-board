
package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryGroupController {

  private final FaqCategoryGroupService faqCategoryGroupService;

  @GetMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.OK)
  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> list() {
    return faqCategoryGroupService.listFaqCategory();
  }

  @PostMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.CREATED)
  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse create(@RequestBody @Valid final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    return faqCategoryGroupService.createFaqCategory(request);
  }

  @PatchMapping("/faqsGroup/{faqCategoryGroupId}")
  public FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse update(
      @PathVariable final Long faqCategoryGroupId,
      @RequestBody @Valid final FaqCategoryGroupDto.UpdateFaqCategoryRequest request
  ) {
    return faqCategoryGroupService.updateFaqCategory(faqCategoryGroupId, request);
  }

  @DeleteMapping("/faqsGroup/{faqCategoryGroupId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long faqCategoryGroupId) {
    faqCategoryGroupService.deleteFaqCategory(faqCategoryGroupId);
  }

}

//TODO : FaqType enum 기능 구현 =>  java.sql.SQLException: Incorrect integer value: 'USER_FAQ' for column `admin`.`faq_category_group`.`faq_type` at row 1
//TODO : 노출여부 => enable true / false 기능 구현 =>
//TODO : 페이징 처리 select * from faq limit 0, 10;

