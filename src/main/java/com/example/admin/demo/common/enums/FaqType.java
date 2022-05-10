package com.example.admin.demo.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FaqType implements EnumMapperType {
  STORE_FAQ("가맹점"),
  USER_FAQ("사용자"),
  ;

  private final String title;

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getValue() {
    return null;
  }

}
