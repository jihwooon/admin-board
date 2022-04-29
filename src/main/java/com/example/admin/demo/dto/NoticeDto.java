package com.example.admin.demo.dto;

import com.example.admin.demo.domain.notice.Notice;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class NoticeDto {

  @Getter
  @Setter
  public static class CreateNoticeRequest {

    @NotBlank
    private String noticeTitle;

    @NotBlank
    private String noticeContents;

  }

  @Getter
  public static class getNoticeResponse {

    private String noticeTitle;

    private LocalDateTime createTime;

    private String noticeContents;

    public getNoticeResponse(final Notice notice) {
      this.noticeTitle = notice.getNoticeTitle();
      this.noticeContents = notice.getNoticeContents();
      this.createTime = notice.getCreateTime();
    }

    public static getNoticeResponse of(final Notice notice) {
      return new getNoticeResponse(notice);
    }
  }

}
