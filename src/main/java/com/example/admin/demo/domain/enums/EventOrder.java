package com.example.admin.demo.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventOrder implements EnumMapperType {
  CREATE_DESC("등록일최근순"),
  CREATE_ASC("등록일옛날순")
  ;

  private final String title;

  @Override
  public String getTitle() {
    return title;
  }
}
