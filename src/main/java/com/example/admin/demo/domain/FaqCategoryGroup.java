package com.example.admin.demo.domain;

import com.example.admin.demo.dto.FaqCategoryGroupDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqCategoryGroup extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  @Enumerated(EnumType.STRING)
  private FaqType faqType;

  @OneToMany(mappedBy = "faqCategoryGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FaqCategory> faqCategories = new ArrayList<>();

  @Builder(builderClassName = "CreateFaqCategoryGroup", builderMethodName = "CreateFaqCategoryGroup")
  public FaqCategoryGroup(final FaqType faqType,
                          final String title) {
    this.faqType = faqType;
    this.title = title;
  }

  public void updateFaqCategory(final FaqCategoryGroupDto.UpdateFaqCategoryRequest request) {
    title = request.getTitle();
  }
}
