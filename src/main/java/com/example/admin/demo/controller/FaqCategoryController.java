package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FaqCategoryController {

  private final FaqCategoryService faqCategoryService;

  @GetMapping("/faqs")
  public FaqCategoryDto.ListFaqCategoryResponsePage list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                                         @ModelAttribute FaqCategoryDto.SearchConditionRequestDto request) {
    return faqCategoryService.listFaqCategory(PageRequest.of(page, size), request);
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

  @PutMapping("/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}/expose")
  public void updateExposeById(@PathVariable Long faqCategoryGroupId,
                               @PathVariable Long faqId,
                               @RequestBody FaqCategoryDto.UpdateExposeRequest expose) {

    faqCategoryService.updateExposeById(faqCategoryGroupId, faqId, expose);
  }
}
