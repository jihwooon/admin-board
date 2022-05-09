package com.example.admin.demo.dto;

import com.example.admin.demo.domain.faqCategory.FaqCategory;
import com.example.admin.demo.domain.faqCategory.FaqCategoryGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaqCategoryDto {

  @Getter
  public static class ListFaqCategoryResponsePage {
    private long totalElements;
    private int totalPages;
    private List<SearchResultResponse> replayContents;

    private ListFaqCategoryResponsePage(final Page<FaqCategory> faqCategories) {
      this.totalElements = faqCategories.getTotalElements();
      this.totalPages = faqCategories.getTotalPages();
      this.replayContents = SearchResultResponse.of(faqCategories.getContent());
    }

    public static ListFaqCategoryResponsePage of(final Page<FaqCategory> faqCategories) {
      return new ListFaqCategoryResponsePage(faqCategories);
    }
  }

  @Getter
  public static class SearchResultResponse {

    private Long faqCategoryId;
    private FaqCategoryGroupDto.ListFaqCategoryGroupResponse faqCategoryGroup;
    private String title;
    private LocalDateTime createTime;
    private Boolean expose;

    public SearchResultResponse(final FaqCategory faqCategory) {
      this.faqCategoryId = faqCategory.getId();
      this.faqCategoryGroup = FaqCategoryGroupDto.ListFaqCategoryGroupResponse.of(faqCategory.getFaqCategoryGroup());
      this.title = faqCategory.getFaqTitle();
      this.createTime = faqCategory.getCreateTime();
      this.expose = faqCategory.isExpose();
    }

    public static SearchResultResponse of(final FaqCategory faqCategory) {
      return new SearchResultResponse(faqCategory);
    }

    public static List<SearchResultResponse> of(List<FaqCategory> faqCategories) {
      return faqCategories.stream()
          .map(o -> new SearchResultResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  public static class DetailFaqCategoryResponse {
    private String faqCategoryGroupTitle;
    private String title;
    private String content;

    public DetailFaqCategoryResponse(final FaqCategoryGroup faqCategoryGroup,
                                     final FaqCategory faqCategory) {
      this.faqCategoryGroupTitle = faqCategoryGroup.getTitle();
      this.title = faqCategory.getFaqTitle();
      this.content = faqCategory.getReplayContent();
    }

    public static DetailFaqCategoryResponse of(final FaqCategoryGroup faqCategoryGroup,
                                               final FaqCategory faqCategory) {
      return new DetailFaqCategoryResponse(faqCategoryGroup, faqCategory);
    }
  }

  @Getter
  public static class CreateFaqCategoryRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
  }

  @Getter
  public static class CreateFaqCategoryResponse {
    private String title;
    private String content;

    public CreateFaqCategoryResponse(final FaqCategory faqCategory) {
      this.title = faqCategory.getFaqTitle();
      this.content = faqCategory.getReplayContent();
    }

    public static CreateFaqCategoryResponse of(final FaqCategory faqCategory) {
      return new CreateFaqCategoryResponse(faqCategory);
    }
  }

  @Getter
  public static class DeleteFaqCategoryRequest {

    @NotEmpty
    List<Long> faqCategories = new ArrayList<>();
  }


  @Getter
  @Setter
  public static class UpdateExposeRequest {

    @NotNull
    private Boolean expose;
  }

  @Getter
  @Setter
  public static class SearchRequest {

    private Long faqCategoryGroupId;
  }

}
