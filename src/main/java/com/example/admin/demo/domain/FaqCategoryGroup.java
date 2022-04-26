package com.example.admin.demo.domain;

import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
  private boolean expose = false;

  @OneToMany(mappedBy = "faqCategoryGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FaqCategory> faqCategoryList = new ArrayList<>();

  @Builder
  public FaqCategoryGroup(String title) {
    this.title = title;
  }
}
