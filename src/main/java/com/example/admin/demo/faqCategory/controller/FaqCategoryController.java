package com.example.admin.demo.faqCategory.controller;

import com.example.admin.demo.faqCategory.application.FaqCategoryService;
import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FaqCategoryController {

  private final FaqCategoryService faqCategoryService;

  @PostMapping("/faqsGroup/{faqCategoryGroupId}/faqs")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createFaqCategory(@PathVariable final Long faqCategoryGroupId,
                                @RequestBody @Valid final FaqCategoryDto.CreateFaqCategoryRequest request) {
    return faqCategoryService.createFaqCategory(faqCategoryGroupId, request);
  }

  @GetMapping("/faqs")
  @ResponseStatus(HttpStatus.OK)
  public CommonDto.PageResponse getFaqCategories(@RequestParam(value = "page", defaultValue = "0") final int page,
                                                 @RequestParam(value = "size", defaultValue = "10") final int size,
                                                 @ModelAttribute final FaqCategoryDto.SearchRequest request) {
    return faqCategoryService.getFaqCategories(PageRequest.of(page, size), request);
  }

  @GetMapping("/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}")
  @ResponseStatus(HttpStatus.OK)
  public FaqCategoryDto.DetailFaqCategoryResponse getFaqCategory(@PathVariable final Long faqCategoryGroupId,
                                                                 @PathVariable final Long faqId) {
    return faqCategoryService.getFaqCategory(faqCategoryGroupId, faqId);
  }

  @PutMapping("/faqs/{faqId}/expose")
  @ResponseStatus(HttpStatus.OK)
  public void updateExposeById(@PathVariable final Long faqId,
                               @RequestBody final FaqCategoryDto.UpdateExposeRequest expose) {

    faqCategoryService.updateExposeById(faqId, expose);
  }

  @DeleteMapping("/faqs/{faqId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteFaqCategoryById(@PathVariable final Long faqId) {
    faqCategoryService.deleteById(faqId);
  }

  @DeleteMapping("/faqs")
  @ResponseStatus(HttpStatus.OK)
  public void deleteFaqCategories(@RequestBody final FaqCategoryDto.DeleteFaqCategoryRequest request) {
    faqCategoryService.deleteFaqCategories(request);
  }

}
