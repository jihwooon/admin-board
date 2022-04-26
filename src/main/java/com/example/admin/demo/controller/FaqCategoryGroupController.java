
package com.example.admin.demo.controller;

import com.example.admin.demo.application.Impl.FaqCategoryGroupServiceImpl;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryGroupController {

  private final FaqCategoryGroupServiceImpl faqCategoryGroupServiceImpl;

  @GetMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.OK)
  public List<FaqCategoryGroupDto.ListFaqCategoryGroupResponse> list() {
    return faqCategoryGroupServiceImpl.listFaqCategory();
  }

  @PostMapping("/faqsGroup")
  @ResponseStatus(HttpStatus.CREATED)
  public FaqCategoryGroupDto.CreateFaqCategoryGroupResponse create(@RequestBody @Valid final FaqCategoryGroupDto.CreateFaqCategoryGroupRequest request) {
    return faqCategoryGroupServiceImpl.createFaqCategory(request);
  }

  @PatchMapping("/faqsGroup/{faqCategoryGroupId}")
  public FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse update(
      @PathVariable final Long faqCategoryGroupId,
      @RequestBody @Valid final FaqCategoryGroupDto.UpdateFaqCategoryRequest request
  ) {
    return faqCategoryGroupServiceImpl.updateFaqCategory(faqCategoryGroupId, request);
  }

  @DeleteMapping("/faqsGroup/{faqCategoryGroupId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long faqCategoryGroupId) {
    faqCategoryGroupServiceImpl.deleteFaqCategory(faqCategoryGroupId);
  }

}
