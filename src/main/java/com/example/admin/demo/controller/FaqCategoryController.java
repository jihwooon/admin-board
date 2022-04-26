package com.example.admin.demo.controller;

import com.example.admin.demo.application.Impl.FaqCategoryServiceImpl;
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

  private final FaqCategoryServiceImpl faqCategoryServiceImpl;

  //TODO : FAQ 목록 - 완료
  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list() {
    return faqCategoryServiceImpl.listFaqCategory();
  }

  //TODO : FAQ 상세 - 완료
  @GetMapping("/faqs/{faqId}")
  public FaqCategoryDto.DetailFaqCategoryResponse detail(@PathVariable Long faqId) {
    return faqCategoryServiceImpl.detailFaqCategory(faqId);
  }

  //TODO : FAQ 등록 - 완료
  @PostMapping("/faqs")
  public FaqCategoryDto.CreateFaqCategoryResponse create(@RequestBody FaqCategoryDto.CreateFaqCategoryRequest request) {
    return faqCategoryServiceImpl.createFaqCategory(request);
  }

  //TODO : FAQ 삭제 - 완료
  @DeleteMapping("/faqs/{faqId}")
  public void delete(@PathVariable Long faqId) {
    faqCategoryServiceImpl.deleteFaqCategory(faqId);
  }
}
