package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FaqCategoryController {

  private final FaqCategoryService faqCategoryService;

  @PostMapping("/faqsGroup/{faqCategoryGroupId}/faqs")
  public void createFaqCategory(
      @PathVariable final Long faqCategoryGroupId,
      @RequestBody @Valid final FaqCategoryDto.CreateFaqCategoryRequest request) {
    faqCategoryService.createFaqCategory(faqCategoryGroupId, request);
  }

  @GetMapping("/faqs")
  public FaqCategoryDto.ListFaqCategoryResponsePage getFaqCategories(@RequestParam(value = "page", defaultValue = "0") final int page,
                                                                     @RequestParam(value = "size", defaultValue = "10") final int size,
                                                                     @ModelAttribute final FaqCategoryDto.SearchConditionRequestDto request) {
    return faqCategoryService.getFaqCategories(PageRequest.of(page, size), request);
  }

  @GetMapping("/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}")
  public FaqCategoryDto.DetailFaqCategoryResponse getFaqCategory(@PathVariable final Long faqCategoryGroupId,
                                                                 @PathVariable final Long faqId) {
    return faqCategoryService.getFaqCategory(faqCategoryGroupId, faqId);
  }

  @PutMapping("/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}/expose")
  public void updateExposeById(@PathVariable final Long faqCategoryGroupId,
                               @PathVariable final Long faqId,
                               @RequestBody final FaqCategoryDto.UpdateExposeRequest expose) {

    faqCategoryService.updateExposeById(faqCategoryGroupId, faqId, expose);
  }

  @DeleteMapping("/faqs/{faqId}")
  public void deleteFaqCategoryById(@PathVariable final Long faqId) {
    faqCategoryService.deleteFaqCategoryById(faqId);
  }

  @DeleteMapping("/faqs")
  public void deleteFaqCategories(final FaqCategoryDto.DeleteFaqCategoryRequest request) {
    faqCategoryService.deleteFaqCategories(request);
  }

}
