package com.example.admin.demo.notice.application.Impl;

import com.example.admin.demo.notice.domain.Notice;
import com.example.admin.demo.notice.dto.NoticeDto;
import com.example.admin.demo.notice.repository.NoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active:integration")
class NoticeServiceImplTest {

  @Autowired
  private NoticeServiceImpl noticeService;

  @Autowired
  private NoticeRepository noticeRepository;

  @AfterEach
  void after() {
    noticeRepository.deleteAllInBatch();
  }

  @Test
  void createNotice() {
    NoticeDto.CreateNoticeRequest createNoticeRequest = new NoticeDto.CreateNoticeRequest();
    createNoticeRequest.setNoticeTitle("제목");
    createNoticeRequest.setNoticeContents("내용");

    Notice notice = noticeRepository.save(Notice.builder()
        .noticeTitle(createNoticeRequest.getNoticeTitle())
        .noticeContents(createNoticeRequest.getNoticeContents())
        .build());

    noticeService.createNotice(createNoticeRequest);

    assertThat(createNoticeRequest.getNoticeTitle()).isEqualTo(notice.getNoticeTitle());
    assertThat(createNoticeRequest.getNoticeContents()).isEqualTo(notice.getNoticeContents());
  }

  @Test
  void updateNotice() {
    Long noticeId = noticeRepository.save(Notice.builder()
        .noticeTitle("제목")
        .noticeContents("내용")
        .build())
        .getNoticeId();

    NoticeDto.UpdateNoticeRequest updateNoticeRequest = new NoticeDto.UpdateNoticeRequest();
    updateNoticeRequest.setNoticeTitle("수정 제목");
    updateNoticeRequest.setNoticeContents("수정 내용");

    noticeService.updateNotice(noticeId, updateNoticeRequest);

    Notice notice = noticeRepository.findById(noticeId).get();

    assertThat(notice.getNoticeTitle()).isEqualTo(updateNoticeRequest.getNoticeTitle());
    assertThat(notice.getNoticeContents()).isEqualTo(updateNoticeRequest.getNoticeContents());
  }

  @Test
  void getNoticeById() {
    Long noticeId = noticeRepository.save(Notice.builder()
        .noticeTitle("제목")
        .noticeContents("내용")
        .build())
        .getNoticeId();

    noticeService.getNoticeById(noticeId);

    Notice notice = noticeRepository.findById(noticeId).get();

    assertThat(notice.getNoticeTitle()).isEqualTo("제목");
    assertThat(notice.getNoticeContents()).isEqualTo("내용");
  }

  @Test
  void updateExposeIsFalse() {
    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    Long noticeId = noticeRepository.save(Notice.builder()
        .expose(updateExposeRequest.getExpose())
        .build())
        .getNoticeId();

    noticeService.updateExposeById(noticeId, updateExposeRequest);

    Notice notice = noticeRepository.findById(noticeId).get();
    assertThat(notice.isExpose()).isFalse();

  }

  @Test
  void updateExposeIsTrue() {
    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    Long noticeId = noticeRepository.save(Notice.builder()
        .expose(updateExposeRequest.getExpose())
        .build())
        .getNoticeId();

    noticeService.updateExposeById(noticeId, updateExposeRequest);

    Notice notice = noticeRepository.findById(noticeId).get();
    assertThat(notice.isExpose()).isTrue();
  }

}
