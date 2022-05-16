package com.example.admin.demo.notice.application.Impl;

import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.common.error.NoticeNotFoundException;
import com.example.admin.demo.notice.application.NoticeService;
import com.example.admin.demo.notice.domain.Notice;
import com.example.admin.demo.notice.dto.NoticeDto;
import com.example.admin.demo.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeRepository noticeRepository;

  @Override
  public Long createNotice(final NoticeDto.CreateNoticeRequest request) {
    Notice notice = Notice.builder()
        .noticeTitle(request.getNoticeTitle())
        .noticeContents(request.getNoticeContents())
        .build();

    return noticeRepository.save(notice).getNoticeId();
  }

  @Override
  public CommonDto.PageResponse getNotices(final Pageable pageable,
                                           final NoticeDto.SearchRequest searchRequest) {

      return CommonDto.PageResponse.of(noticeRepository.findNoticeByCondition(pageable, searchRequest));
    }

  @Override
  public Notice getNoticeById(final Long noticeId) {
    return noticeRepository.findById(noticeId)
        .orElseThrow(() -> new NoticeNotFoundException("Not Found Id"));
  }

  @Override
  public void updateNotice(final Long noticeId,
                           final NoticeDto.UpdateNoticeRequest updateRequest) {

    Notice notice = getNoticeById(noticeId);
    notice.changeNotice(updateRequest);

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
}

