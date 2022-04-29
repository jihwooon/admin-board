package com.example.admin.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class NoticeDto {

  @Getter
  @Setter
  public static class CreateNoticeRequest {

    @NotBlank
    private String noticeTitle;

    @NotBlank
    private String noticeContents;

  }
}
