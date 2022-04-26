package com.example.admin.demo.controller;

import com.example.admin.demo.application.Impl.FaqCategoryServiceImpl;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryController {

  private final FaqCategoryServiceImpl faqCategoryServiceImpl;

  //TODO : FAQ 목록 - 완료
  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list () {
    return faqCategoryServiceImpl.listFaqCategory();
  }

  //TODO : FAQ 상세

  //TODO : FAQ 등록

  //TODO : FAQ 수정

  //TODO : FAQ 삭제
}
