package com.example.admin.demo.faqCategory.domain;

import com.example.admin.demo.common.BaseEntity;
import com.example.admin.demo.common.enums.FaqType;
import com.example.admin.demo.faqCategory.dto.FaqCategoryGroupDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqCategoryGroup extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  @Enumerated(EnumType.STRING)
  private FaqType faqType;

  private boolean enable = true;

  @OneToMany(mappedBy = "faqCategoryGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FaqCategory> faqCategories = new ArrayList<>();

  @Builder
  public FaqCategoryGroup(final FaqType faqType,
                          final String title) {
    this.faqType = faqType;
    this.title = title;
  }


  public void changeEnable(boolean enable) {
    this.enable = enable;
  }

  public void updateFaqCategory(final FaqCategoryGroupDto.UpdateFaqCategoryRequest request) {
    title = request.getFaqCategoryGroupTitle();
  }
}
