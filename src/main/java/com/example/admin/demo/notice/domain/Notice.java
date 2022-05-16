package com.example.admin.demo.notice.domain;

import com.example.admin.demo.common.BaseEntity;
import com.example.admin.demo.notice.dto.NoticeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long noticeId;

  private String noticeTitle;

  private String noticeContents;

  private boolean expose = false;

  private boolean enable = true;

  @Builder
  public Notice(final String noticeTitle,
                final String noticeContents,
                final boolean expose,
                final boolean enable) {

    this.noticeTitle = noticeTitle;
    this.noticeContents = noticeContents;
    this.expose = expose;
    this.enable = enable;
  }

  public void changeNotice(final NoticeDto.UpdateNoticeRequest updateNoticeRequest) {
    this.noticeTitle = updateNoticeRequest.getNoticeTitle();
    this.noticeContents = updateNoticeRequest.getNoticeContents();
  }

  public void changeEnable(final boolean enable) {
    this.enable = enable;
  }

  public void changeExpose(final NoticeDto.UpdateExposeRequest updateExpose) {
    this.expose = updateExpose.getExpose();
  }
}
