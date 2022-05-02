package com.example.admin.demo.application;

import com.example.admin.demo.domain.notice.Notice;
import com.example.admin.demo.dto.NoticeDto;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

  void createNotice(NoticeDto.CreateNoticeRequest request);

  Notice getNoticeById(Long noticeId);

  void updateNotice(Long noticeId,
                    NoticeDto.UpdateNoticeRequest request);

  void updateExposeById(Long noticeId,
                        NoticeDto.UpdateExposeRequest updateExpose);

  void deleteById(Long noticeId);

  void deleteNotices(NoticeDto.DeleteTotalNoticeRequest request);

  NoticeDto.PageNoticeResponse getNotices(Pageable pageable,
                                          NoticeDto.SearchRequest searchRequest);
}
