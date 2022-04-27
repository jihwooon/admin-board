package com.example.admin.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqCategory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private boolean expose = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faqCategoryGroup_id")
  private FaqCategoryGroup faqCategoryGroup;

  public void setFaqCategoryGroup(FaqCategoryGroup faqCategoryGroup) {
    this.faqCategoryGroup = faqCategoryGroup;
    faqCategoryGroup.getFaqCategories().add(this);
  }

  @Builder
  public FaqCategory(String title, String content, FaqCategoryGroup faqCategoryGroup) {
    this.title = title;
    this.content = content;
    this.faqCategoryGroup = faqCategoryGroup;
  }


  public void changeExpose(final boolean expose) {
    this.expose = expose;
  }

  public void changeFaqCategoryGroup(final FaqCategoryGroup faqCategoryGroup) {
    this.faqCategoryGroup = faqCategoryGroup;
  }
}
