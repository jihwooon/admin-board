package com.example.admin.demo.faqCategory.dto;

import com.example.admin.demo.faqCategory.domain.FaqCategory;
import com.example.admin.demo.faqCategory.domain.FaqCategoryGroup;
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
    private String faqCategoryTitle;
    private LocalDateTime createTime;
    private Boolean expose;
    private FaqCategoryGroupDto.ListFaqCategoryGroupResponse faqCategoryGroups;

    public SearchResultResponse(final FaqCategory faqCategory) {
      this.faqCategoryId = faqCategory.getId();
      this.faqCategoryTitle = faqCategory.getFaqTitle();
      this.createTime = faqCategory.getCreateTime();
      this.expose = faqCategory.isExpose();
      this.faqCategoryGroups = FaqCategoryGroupDto.ListFaqCategoryGroupResponse.of(faqCategory.getFaqCategoryGroup());
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
    private String faqCategoryTitle;
    private String content;

    public DetailFaqCategoryResponse(final FaqCategoryGroup faqCategoryGroup,
                                     final FaqCategory faqCategory) {
      this.faqCategoryGroupTitle = faqCategoryGroup.getTitle();
      this.faqCategoryTitle = faqCategory.getFaqTitle();
      this.content = faqCategory.getReplayContent();
    }

    public static DetailFaqCategoryResponse of(final FaqCategoryGroup faqCategoryGroup,
                                               final FaqCategory faqCategory) {
      return new DetailFaqCategoryResponse(faqCategoryGroup, faqCategory);
    }
  }

  @Getter
  @Setter
  public static class CreateFaqCategoryRequest {
    @NotBlank
    private String faqCategoryTitle;

    @NotBlank
    private String faqCategoryContent;
  }

  @Getter
  public static class CreateFaqCategoryResponse {
    private String faqCategoryTitle;
    private String faqCategoryContent;

    public CreateFaqCategoryResponse(final FaqCategory faqCategory) {
      this.faqCategoryTitle = faqCategory.getFaqTitle();
      this.faqCategoryContent = faqCategory.getReplayContent();
    }

    public static CreateFaqCategoryResponse of(final FaqCategory faqCategory) {
      return new CreateFaqCategoryResponse(faqCategory);
    }
  }

  @Getter
  @Setter
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
