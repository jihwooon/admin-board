package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.NoticeService;
import com.example.admin.demo.domain.notice.Notice;
import com.example.admin.demo.dto.NoticeDto;
import com.example.admin.demo.error.NoticeNotFoundException;
import com.example.admin.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
  public void updateNotice(final Long noticeId,
                           final NoticeDto.UpdateNoticeRequest request) {
    Notice notice = getNoticeById(noticeId);
    notice.changeNotice(request.getNoticeTitle(),
        request.getNoticeContents());

    noticeRepository.save(notice);
  }

  @Override
  public void updateExposeById(final Long noticeId,
                               final NoticeDto.UpdateExposeRequest updateExpose) {
    Notice notice = getNoticeById(noticeId);
    notice.changeExpose(updateExpose);

    noticeRepository.save(notice);
  }

  @Override
  public void deleteById(final Long noticeId) {
    Notice notice = deleteNoticeById(noticeId);
    notice.changeEnable(false);

    noticeRepository.save(notice);
  }

  @Override
  public void deleteNotices(final NoticeDto.DeleteTotalNoticeRequest request) {
    List<Notice> notices = noticeRepository.findByNoticeIdInAndEnableIsTrue(request.getNoticesId());

    for (Notice notice : notices) {
      notice.changeEnable(false);
    }

    noticeRepository.saveAll(notices);
  }

  private Notice deleteNoticeById(final Long noticeId) {
    return noticeRepository.findByNoticeIdAndEnableIsTrue(noticeId)
        .orElseThrow(() -> new NoticeNotFoundException("Not Found Id"));
  }

  @Override
  public NoticeDto.PageNoticeResponse getNotices(final Pageable pageable,
                                                 final NoticeDto.SearchRequest searchRequest) {

    if (searchRequest.getNoticeTitle() == null) {
      return NoticeDto.PageNoticeResponse.of(noticeRepository.findAll(pageable));
    } else {
      String notice = searchRequest.getNoticeTitle();
      return NoticeDto.PageNoticeResponse.of(noticeRepository.findAllByNoticeTitleContaining(pageable, notice));
    }
  }
}

