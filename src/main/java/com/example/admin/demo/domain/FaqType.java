package com.example.admin.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  FaqType implements EnumMapperType {
  STORE_FAQ("가맹점"),
  USER_FAQ("사용자"),
  ;

  private String title;

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getValue() {
    return null;
  }
}
