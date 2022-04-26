
package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqCategoryGroupController {

  private final FaqCategoryGroupService faqCategoryGroupService;

  //TODO : ListDTO Title 와 modifiedDate 받아서 List 하기
  @GetMapping("/faqs")
  public List<FaqCategoryDto.ListFaqCategoryResponse> list() {
    return faqCategoryGroupService.getList();
  }

  //TODO : CreateDTO Title 등록하기


  //TODO : UpdateDTO Title 수정하기


  //TODO : Delete 카테고리 삭제하기

}
