package com.example.admin.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  FaqType {
  STORE_FAQ("가맹점"),
  APPUSER_FAQ("사용자"),
  ;

  private String title;

}
