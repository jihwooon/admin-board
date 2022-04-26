
package com.example.admin.demo.controller;

import com.example.admin.demo.application.Impl.FaqCategoryGroupServiceImpl;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryGroupController {

  private final FaqCategoryGroupServiceImpl faqCategoryGroupServiceimpl;

  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list() {
    return faqCategoryGroupServiceimpl.listFaqCategory();
  }

  @PostMapping("/faqs")
  public FaqCategoryDto.CreateFaqCategoryResponse create(@RequestBody @Valid final FaqCategoryDto.CreateFaqCategoryRequest request) {
    return faqCategoryGroupServiceimpl.createFaqCategory(request);
  }

  //TODO : UpdateDTO Title 수정하기
  @PatchMapping("/faqs/{faqCategoryGroupId}")
  public FaqCategoryDto.UpdateFaqCategoryResponse update(
                          @PathVariable final Long faqCategoryGroupId,
                          @RequestBody @Valid final FaqCategoryDto.UpdateFaqCategoryRequest request
  ) {
    return faqCategoryGroupServiceimpl.updateFaqCategory(faqCategoryGroupId, request);
  }


  //TODO : Delete 카테고리 삭제하기

}
