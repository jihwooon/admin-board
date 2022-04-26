package com.example.admin.demo.dto;

import com.example.admin.demo.domain.FaqCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public ListFaqCategoryResponse(final FaqCategory faqCategory) {
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

  public static class DetailFaqCategoryResponse {
    //    private FaqCategoryGroup faqCategoryGroupTitle //카테고리 구분
    private String title;
    private String content;

    public DetailFaqCategoryResponse(final FaqCategory faqCategory) {
      this.title = faqCategory.getTitle();
      this.content = faqCategory.getContent();
    }

    public static DetailFaqCategoryResponse of(final FaqCategory faqCategory) {
      return new DetailFaqCategoryResponse(faqCategory);
    }

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CreateFaqCategoryRequest {
    private String title;
    private String content;

    public CreateFaqCategoryRequest(final FaqCategory faqCategory) {
      this.title = faqCategory.getTitle();
      this.content = faqCategory.getContent();
    }
  }

  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CreateFaqCategoryResponse {

    private String title;
    private String content;

    public CreateFaqCategoryResponse(final FaqCategory faqCategory) {
      this.title = faqCategory.getTitle();
      this.content = faqCategory.getContent();
    }

    public static CreateFaqCategoryResponse of(final FaqCategory faqCategory) {
      return new CreateFaqCategoryResponse(faqCategory);
    }
  }
}
