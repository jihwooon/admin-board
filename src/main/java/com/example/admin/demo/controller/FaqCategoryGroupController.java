//TODO : 테스트 케이스 작성할 때 DTO를 반환 하지만 프론트와 협업 할 때는 API 값이 필요 없으면

package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
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
  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse createFaqCategoryGroup(@RequestBody @Valid final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    return faqCategoryGroupService.createFaqCategory(request);
  }

  @GetMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.OK)
  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> getFaqCategoryGroup(@PageableDefault Pageable pageable) {
    return faqCategoryGroupService.listFaqCategory(pageable);
  }

  @PutMapping("/faqsGroup/{faqCategoryGroupId}")
  public void updateFaqCategoryGroup(
      @PathVariable final Long faqCategoryGroupId,
      @RequestBody @Valid final FaqCategoryGroupDto.UpdateFaqCategoryRequest request
  ) {
    faqCategoryGroupService.updateFaqCategory(faqCategoryGroupId, request);
  }

  @DeleteMapping("/faqsGroup/{faqCategoryGroupId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteFaqCategoryGroup(@PathVariable final Long faqCategoryGroupId) {
    faqCategoryGroupService.deleteFaqCategory(faqCategoryGroupId);
  }

}


