package com.example.admin.demo.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColorType implements EnumMapperType {
  WHITE("흰색"),
  BLACK("검은색");

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
