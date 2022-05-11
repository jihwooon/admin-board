package com.example.admin.demo.faqCategory.domain;

import com.example.admin.demo.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqCategory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String faqTitle;

  private String replayContent;

  private boolean expose = false;

  private boolean enable = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faqCategoryGroup_id")
  private FaqCategoryGroup faqCategoryGroup;

  public void setFaqCategoryGroup(final FaqCategoryGroup faqCategoryGroup) {
    this.faqCategoryGroup = faqCategoryGroup;
    faqCategoryGroup.getFaqCategories().add(this);
  }

  @Builder
  public FaqCategory(final String faqTitle,
                     final String replayContent,
                     final FaqCategoryGroup faqCategoryGroup,
                     final boolean expose) {

    this.faqTitle = faqTitle;
    this.replayContent = replayContent;
    this.faqCategoryGroup = faqCategoryGroup;
    this.expose = expose;
  }

  public void changeExpose(final boolean expose) {
    this.expose = expose;
  }

  public void changeEnable(final boolean enable) {
    this.enable = enable;
  }

  public void changeFaqCategoryGroup(final FaqCategoryGroup faqCategoryGroup) {
    this.faqCategoryGroup = faqCategoryGroup;
  }
}
