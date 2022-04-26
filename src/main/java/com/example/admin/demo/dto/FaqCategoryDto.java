package com.example.admin.demo.dto;

import com.example.admin.demo.domain.FaqCategoryGroup;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FaqCategoryDto {

  @Getter
  public static class ListFaqCategoryResponse {

    private String title;
    private LocalDateTime modifiedDate;


    public ListFaqCategoryResponse(FaqCategoryGroup faqCategoryGroup) {
      this.title = faqCategoryGroup.getTitle();
      this.modifiedDate = faqCategoryGroup.getModifiedDate();
    }

    public static List<ListFaqCategoryResponse> of(final List<FaqCategoryGroup> faqCategoryGroups) {
      return faqCategoryGroups.stream()
          .map(o -> new ListFaqCategoryResponse(o))
          .collect(Collectors.toList());
    }

  }
}
