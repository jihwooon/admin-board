package com.example.admin.demo.application;

import com.example.admin.demo.domain.notice.Notice;
import com.example.admin.demo.dto.NoticeDto;

public interface NoticeService {

  void createNotice(NoticeDto.CreateNoticeRequest request);

  Notice getNoticeById(Long id);

  void deleteById(Long noticeId);

  void deleteNotices(NoticeDto.DeleteTotalNoticeRequest request);
}
