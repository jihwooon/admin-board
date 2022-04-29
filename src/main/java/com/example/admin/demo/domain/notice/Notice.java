package com.example.admin.demo.domain.notice;

import com.example.admin.demo.domain.common.BaseEntity;
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
  private Long noticeid;

  private String noticeTitle;

  private String noticeContents;

  private boolean expose = true;

  private boolean enable = false;

  @Builder
  public Notice(String noticeTitle, String noticeContents, boolean expose, boolean enable) {
    this.noticeTitle = noticeTitle;
    this.noticeContents = noticeContents;
    this.expose = expose;
    this.enable = enable;
  }
}
