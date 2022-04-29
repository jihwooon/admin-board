package com.example.admin.demo.domain.notice;

import com.example.admin.demo.domain.common.BaseEntity;
import com.example.admin.demo.dto.NoticeDto;
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
                final String noticeContents) {

    this.noticeTitle = noticeTitle;
    this.noticeContents = noticeContents;
  }

  public void changeEnable(final boolean enable) {
    this.enable = enable;
  }

  public void changeNotice(final String noticeTitle,
                           final String noticeContents) {
    this.noticeTitle = noticeTitle;
    this.noticeContents = noticeContents;
  }

  public void changeExpose(final NoticeDto.UpdateExposeRequest updateExpose) {
    this.expose = updateExpose.getExpose();
  }
}
