package com.example.admin.demo.config;

import com.example.admin.demo.domain.enums.EnumMapperType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements EnumMapperType {
  ADMIN("ADMIN", "관리자"),
  SALES("SALES", "영업 담당자")
  ;

  private String title;
  private String description;

}
