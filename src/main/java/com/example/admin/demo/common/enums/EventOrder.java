package com.example.admin.demo.common.enums;

import com.querydsl.core.types.OrderSpecifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.admin.demo.event.QEvent.event;

@Getter
@AllArgsConstructor
public enum EventOrder implements EnumMapperType {
  CREATE_DESC("등록일 최근순", event.createTime.desc()),
  CREATE_ASC("등록일 옛날순", event.createTime.asc())
  ;

  private final String title;
  private final OrderSpecifier order;

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
