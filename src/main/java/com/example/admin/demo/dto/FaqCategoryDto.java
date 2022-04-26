package com.example.admin.demo.dto;

import com.example.admin.demo.domain.FaqCategoryGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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

  @Getter
  @Setter
  public static class CreateFaqCategoryRequest {

    @NotBlank(message = "등록할 카테고리의 이름을 입력하세요")
    private String title;
  }

  @Getter
  public static class CreateFaqCategoryResponse {
    private String title;
    private LocalDateTime createTime;

    public CreateFaqCategoryResponse(final FaqCategoryGroup faqCategoryGroup) {
      this.title = faqCategoryGroup.getTitle();
      this.createTime = faqCategoryGroup.getCreateTime();
    }

    public static CreateFaqCategoryResponse of(final FaqCategoryGroup faqCategoryGroup) {
      return new CreateFaqCategoryResponse(faqCategoryGroup);
    }

  }

  @Getter
  @Setter
  public static class UpdateFaqCategoryRequest {

    @NotBlank(message = "등록할 카테고리의 이름을 입력하세요")
    private String title;
  }


  @Getter
  public static class UpdateFaqCategoryResponse {
    private String title;
    private LocalDateTime modifiedDate;

    public UpdateFaqCategoryResponse(final FaqCategoryGroup faqCategoryGroup) {
      this.title = faqCategoryGroup.getTitle();
      this.modifiedDate = faqCategoryGroup.getModifiedDate();
    }

    public static UpdateFaqCategoryResponse of(final FaqCategoryGroup faqCategoryGroup) {
      return new UpdateFaqCategoryResponse(faqCategoryGroup);
    }
  }


}
