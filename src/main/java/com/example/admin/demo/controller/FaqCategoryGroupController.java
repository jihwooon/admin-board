
package com.example.admin.demo.controller;

import com.example.admin.demo.application.Impl.FaqCategoryGroupServiceImpl;
import com.example.admin.demo.dto.FaqCategoryDto;
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

  private final FaqCategoryGroupServiceImpl faqCategoryGroupServiceImpl;

  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list() {
    return faqCategoryGroupServiceImpl.listFaqCategory();
  }

  @PostMapping("/faqs")
  @ResponseStatus(HttpStatus.CREATED)
  public FaqCategoryDto.CreateFaqCategoryResponse create(@RequestBody @Valid final FaqCategoryDto.CreateFaqCategoryRequest request) {
    return faqCategoryGroupServiceImpl.createFaqCategory(request);
  }

  //TODO : UpdateDTO Title 수정하기
  @PatchMapping("/faqs/{faqCategoryGroupId}")
  public FaqCategoryDto.UpdateFaqCategoryResponse update(
                          @PathVariable final Long faqCategoryGroupId,
                          @RequestBody @Valid final FaqCategoryDto.UpdateFaqCategoryRequest request
  ) {
    return faqCategoryGroupServiceImpl.updateFaqCategory(faqCategoryGroupId, request);
  }

  //TODO : Delete 카테고리 삭제하기
  @DeleteMapping("/faqs/{faqCategoryGroupId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long faqCategoryGroupId) {
    faqCategoryGroupServiceImpl.deleteFaqCategory(faqCategoryGroupId);
  }

}
