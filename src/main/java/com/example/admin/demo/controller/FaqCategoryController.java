package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryController {

  private final FaqCategoryService faqCategoryService;

  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list() {
    return faqCategoryService.listFaqCategory();
  }

  @GetMapping("/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}")
  public FaqCategoryDto.DetailFaqCategoryResponse detail(
      @PathVariable Long faqCategoryGroupId,
      @PathVariable Long faqId) {
    return faqCategoryService.detailFaqCategory(faqCategoryGroupId, faqId);
  }

  @PostMapping("/faqsGroup/{faqCategoryGroupId}/faqs")
  public FaqCategoryDto.CreateFaqCategoryResponse create(
      @PathVariable Long faqCategoryGroupId,
      @RequestBody FaqCategoryDto.CreateFaqCategoryRequest request) {
    return faqCategoryService.createFaqCategory(faqCategoryGroupId, request);
  }

  @DeleteMapping("/faqs/{faqId}")
  public void delete(@PathVariable Long faqId) {
    faqCategoryService.deleteFaqCategory(faqId);
  }
}
