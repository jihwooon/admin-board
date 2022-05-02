package com.example.admin.demo.dto;

import com.example.admin.demo.domain.notice.Notice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  public static class UpdateNoticeRequest {

    @NotBlank
    private String noticeTitle;

    @NotBlank
    private String noticeContents;
  }

  @Getter
  @Setter
  public static class DeleteTotalNoticeRequest {

    @NotEmpty
    private List<Long> noticesId = new ArrayList<>();
  }

  @Getter
  @Setter
  public static class UpdateExposeRequest {

    @NotNull
    private Boolean expose;
  }

  @Getter
  @Setter
  public static class SearchRequest {

    private Long noticeId;
  }

  @Getter
  public static class ListNoticeResponse {

    private Long noticeId;
    private String noticeTitle;
    private LocalDateTime createTime;
    private Boolean expose;

    public ListNoticeResponse(final Notice notice) {
      this.noticeId = notice.getNoticeId();
      this.noticeTitle = notice.getNoticeTitle();
      this.createTime = notice.getCreateTime();
      this.expose = notice.isExpose();
    }

    public static List<ListNoticeResponse> of(final List<Notice> notices) {
      return notices.stream()
          .map(o -> new ListNoticeResponse(o))
          .collect(Collectors.toList());
    }
  }

  @Getter
  public static class PageNoticeResponse {

    private long totalElements;
    private int totalPages;
    private List<ListNoticeResponse> contents;

    public PageNoticeResponse(final Page<Notice> notices) {
      this.totalElements = notices.getTotalElements();
      this.totalPages = notices.getTotalPages();
      this.contents = ListNoticeResponse.of(notices.getContent());
    }

    public static PageNoticeResponse of(final Page<Notice> notices) {
      return new PageNoticeResponse(notices);
    }

  }
}
