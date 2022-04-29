package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.NoticeService;
import com.example.admin.demo.domain.notice.Notice;
import com.example.admin.demo.dto.NoticeDto;
import com.example.admin.demo.error.NoticeNotFoundException;
import com.example.admin.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeRepository noticeRepository;

  @Override
  public void createNotice(final NoticeDto.CreateNoticeRequest request) {
    Notice notice = Notice.builder()
        .noticeTitle(request.getNoticeTitle())
        .noticeContents(request.getNoticeContents())
        .build();

    noticeRepository.save(notice);
  }

  @Override
  public Notice getNoticeById(final Long noticeId) {
    return noticeRepository.findById(noticeId)
        .orElseThrow(() -> new NoticeNotFoundException("Not Found Id"));
  }

  @Override
  public void deleteById(final Long noticeId) {
    Notice notice = getNoticeById(noticeId);
    notice.changeEnable(false);

    noticeRepository.save(notice);
  }

  @Override
  public void deleteNotices(final NoticeDto.DeleteTotalNoticeRequest request) {
    List<Notice> notices = noticeRepository.findAllById(request.getNotices());

    for (Notice notice : notices) {
      notice.changeEnable(false);
    }

    noticeRepository.saveAll(notices);
  }

}
