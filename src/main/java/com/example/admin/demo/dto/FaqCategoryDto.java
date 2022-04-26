package com.example.admin.demo.dto;

import com.example.admin.demo.domain.FaqCategory;
import com.example.admin.demo.domain.FaqCategoryGroup;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FaqCategoryDto {

  @Getter
  public static class ListFaqCategoryResponse {

    private Long id;
//    private FaqCategoryGroup faqCategoryGroupTitle //카테고리 구분
    private String title;
    private String content;
    private LocalDateTime createTime;

    public ListFaqCategoryResponse(FaqCategory faqCategory) {
      this.id = faqCategory.getId();
      this.title = faqCategory.getTitle();
      this.content = faqCategory.getContent();
      this.createTime = faqCategory.getCreateTime();
    }

    public static List<ListFaqCategoryResponse> of(List<FaqCategory> faqCategories) {
      return faqCategories.stream()
          .map(o -> new ListFaqCategoryResponse(o))
          .collect(Collectors.toList());
    }
  }

}
