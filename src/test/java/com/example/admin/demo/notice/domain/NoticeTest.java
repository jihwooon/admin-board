package com.example.admin.demo.notice.domain;

import com.example.admin.demo.notice.dto.NoticeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeTest {

  private Notice notice;

  @BeforeEach
  void SetUp() {
    notice = Notice.builder()
        .noticeTitle("알림 제목")
        .noticeContents("알림 내용")
        .build();
  }

  @Test
  void createNotice() {
    assertThat(notice.getNoticeTitle()).isEqualTo("알림 제목");
    assertThat(notice.getNoticeContents()).isEqualTo("알림 내용");
  }

  @Test
  void changeNotice() {
    NoticeDto.UpdateNoticeRequest noticeRequest = new NoticeDto.UpdateNoticeRequest();
    noticeRequest.setNoticeTitle("알림 제목 수정");
    noticeRequest.setNoticeContents("알림 내용 수정");

    notice.changeNotice(noticeRequest);

    assertThat(notice.getNoticeTitle()).isEqualTo(noticeRequest.getNoticeTitle());
    assertThat(notice.getNoticeContents()).isEqualTo(noticeRequest.getNoticeContents());
  }

  @Test
  void changeEnableIsTrue() {
    notice = Notice.builder()
        .enable(true)
        .build();

    notice.changeEnable(true);

    assertThat(notice.isEnable()).isTrue();
  }

  @Test
  void changeEnableIsFalse() {
    notice = Notice.builder()
        .enable(false)
        .build();

    notice.changeEnable(false);

    assertThat(notice.isEnable()).isFalse();
  }

  @Test
  void changeEnableIsTrueAndFalse() {
    notice = Notice.builder()
        .enable(true)
        .build();

    notice.changeEnable(false);

    assertThat(notice.isEnable()).isFalse();
  }

  @Test
  void changeEnableIsFalseAndTrue() {
    notice = Notice.builder()
        .enable(false)
        .build();

    notice.changeEnable(true);

    assertThat(notice.isEnable()).isTrue();
  }

  @Test
  void changeExposeIsTrue() {
    notice = Notice.builder()
        .expose(true)
        .build();

    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    notice.changeExpose(updateExposeRequest);

    assertThat(notice.isExpose()).isTrue();
  }

  @Test
  void changeExposeIsFalse() {
    notice = Notice.builder()
        .expose(false)
        .build();

    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    notice.changeExpose(updateExposeRequest);

    assertThat(notice.isExpose()).isFalse();
  }

  @Test
  void changeExposeIsTrueAndFalse() {
    notice = Notice.builder()
        .expose(true)
        .build();

    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    notice.changeExpose(updateExposeRequest);

    assertThat(notice.isExpose()).isFalse();
  }

  @Test
  void changeExposeIsFalseAndTrue() {
    notice = Notice.builder()
        .expose(false)
        .build();

    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    notice.changeExpose(updateExposeRequest);

    assertThat(notice.isExpose()).isTrue();
  }

}
