package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.NoticeService;
import com.example.admin.demo.domain.notice.Notice;
import com.example.admin.demo.dto.NoticeDto;
import com.example.admin.demo.error.NoticeNotFoundException;
import com.example.admin.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
