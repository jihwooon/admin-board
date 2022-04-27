package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryController {

  private final FaqCategoryService faqCategoryService;

  //TODO : 어노테이션 리스트업
  // Modelattibut 모델 어튜리보트
  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list(@ModelAttribute FaqCategoryDto.SearchConditionRequestDto request,
                                                            Pageable pageable) {
    return faqCategoryService.listFaqCategory(request, pageable);
  }


  @GetMapping("/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}")
  public FaqCategoryDto.DetailFaqCategoryResponse detail(
      @PathVariable Long faqCategoryGroupId,
      @PathVariable Long faqId) {
    return faqCategoryService.detailFaqCategory(faqCategoryGroupId, faqId);
  }

  @PostMapping("/faqsGroup/{faqCategoryGroupId}/faqs")
  public void create(
      @PathVariable Long faqCategoryGroupId,
      @RequestBody FaqCategoryDto.CreateFaqCategoryRequest request) {
    faqCategoryService.createFaqCategory(faqCategoryGroupId, request);
  }

  @DeleteMapping("/faqs/{faqId}")
  public void delete(@PathVariable Long faqId) {
    faqCategoryService.deleteFaqCategory(faqId);
  }
}
