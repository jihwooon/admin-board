package com.example.admin.demo.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusType implements EnumMapperType {
  IN_PROGRESS("진행중"),
  GO_AHEAD("진행예정"),
  END("종료"),
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
