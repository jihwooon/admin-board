package com.example.admin.demo.domain.notice;

import com.example.admin.demo.domain.common.BaseEntity;
import com.example.admin.demo.dto.NoticeDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
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
  public Notice(String noticeTitle, String noticeContents, boolean expose, boolean enable) {
    this.noticeTitle = noticeTitle;
    this.noticeContents = noticeContents;
    this.expose = expose;
    this.enable = enable;
  }

  public void changeEnable(final boolean enable) {
    this.enable = enable;
  }

  public void changeNotice(final NoticeDto.updateNoticeRequest request) {
    this.noticeTitle = request.getNoticeTitle();
    this.noticeContents = request.getNoticeContents();
  }
}
