package com.example.admin.demo.dto;

import com.example.admin.demo.domain.notice.Notice;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
  public static class getNoticeByIdResponse {

    private String noticeTitle;

    private LocalDateTime createTime;

    private String noticeContents;

    public getNoticeByIdResponse(final Notice notice) {
      this.noticeTitle = notice.getNoticeTitle();
      this.noticeContents = notice.getNoticeContents();
      this.createTime = notice.getCreateTime();
    }

    public static getNoticeByIdResponse of(final Notice notice) {
      return new getNoticeByIdResponse(notice);
    }
  }

  @Getter
  @Setter
  public static class DeleteTotalNoticeRequest {

    @NotEmpty
    List<Long> notices = new ArrayList<>();
  }

}
