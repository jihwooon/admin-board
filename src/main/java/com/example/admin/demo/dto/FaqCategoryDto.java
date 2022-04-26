package com.example.admin.demo.dto;

import com.example.admin.demo.domain.FaqCategory;
import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.domain.FaqType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FaqCategoryDto {

  @Getter
  public static class ListFaqCategoryResponse {

    private Long id;
    private String faqCategoryGroupTitle;
    private String title;
    private LocalDateTime createTime;

    public ListFaqCategoryResponse(final FaqCategory faqCategory) {
      this.id = faqCategory.getId();
      this.faqCategoryGroupTitle = faqCategory.getFaqCategoryGroup().getTitle();
      this.title = faqCategory.getTitle();
      this.createTime = faqCategory.getCreateTime();
    }

    public static List<ListFaqCategoryResponse> of(List<FaqCategory> faqCategories) {
      return faqCategories.stream()
          .map(o -> new ListFaqCategoryResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  public static class DetailFaqCategoryResponse {
    private String faqCategoryGroupTitle;
    private String title;
    private String content;

    public DetailFaqCategoryResponse(final FaqCategoryGroup faqCategoryGroup, final FaqCategory faqCategory) {
      this.faqCategoryGroupTitle = faqCategoryGroup.getTitle();
      this.title = faqCategory.getTitle();
      this.content = faqCategory.getContent();
    }

    public static DetailFaqCategoryResponse of(final FaqCategoryGroup faqCategoryGroup, final FaqCategory faqCategory) {
      return new DetailFaqCategoryResponse(faqCategoryGroup, faqCategory);
    }
  }

  @Getter
  @Setter
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
