package com.example.admin.demo.dto;

import com.example.admin.demo.domain.FaqCategory;
import com.example.admin.demo.domain.FaqCategoryGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FaqCategoryDto {

  @Getter
  public static class ListFaqCategoryResponsePage {
    private long totalElements;
    private int totalPages;
    private List<FaqCategoryDto.ListFaqCategoryResponse> contents;

    private ListFaqCategoryResponsePage(final Page<FaqCategory> faqCategories) {
      this.totalElements = faqCategories.getTotalElements();
      this.totalPages = faqCategories.getTotalPages();
      this.contents = FaqCategoryDto.ListFaqCategoryResponse.of(faqCategories.getContent());
    }

    public static ListFaqCategoryResponsePage of(final Page<FaqCategory> faqCategories) {
      return new ListFaqCategoryResponsePage(faqCategories);
    }
  }

  @Getter
  public static class ListFaqCategoryResponse {

    private Long id;
    private FaqCategoryGroupDto.ListFaqCategoryGroupResponse faqCategoryGroup;
    private String title;
    private LocalDateTime createTime;
    private boolean expose;

    public ListFaqCategoryResponse(final FaqCategory faqCategory) {
      this.id = faqCategory.getId();
      this.faqCategoryGroup = FaqCategoryGroupDto.ListFaqCategoryGroupResponse.of(faqCategory.getFaqCategoryGroup());
      this.title = faqCategory.getTitle();
      this.createTime = faqCategory.getCreateTime();
      this.expose = faqCategory.isExpose();
    }

    public static ListFaqCategoryResponse of(final FaqCategory faqCategory) {
      return new ListFaqCategoryResponse(faqCategory);
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
  }

  @Getter
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

  @Getter
  @Setter
  public static class UpdateExposeRequest {

    @NotNull
    private boolean expose;
  }

  @Getter
  @Setter
  public static class SearchConditionRequestDto {
    private Long faqCategoryGroupId;
  }
}
