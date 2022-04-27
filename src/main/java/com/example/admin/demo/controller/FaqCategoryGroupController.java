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
  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> list(@PageableDefault Pageable pageable) {
    return faqCategoryGroupService.listFaqCategory(pageable);
  }

  @PostMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.CREATED)
  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse create(@RequestBody @Valid final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    return faqCategoryGroupService.createFaqCategory(request);
  }

  @PatchMapping("/faqsGroup/{faqCategoryGroupId}") // put/path mapping 차이
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


