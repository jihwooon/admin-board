package com.example.admin.demo.application;

import com.example.admin.demo.domain.notice.Notice;
import com.example.admin.demo.dto.NoticeDto;

public interface NoticeService {

  void createNotice(NoticeDto.CreateNoticeRequest request);

  Notice getNoticeById(Long noticeId);

  void updateNotice(Long noticeId,
                    NoticeDto.UpdateNoticeRequest request);

  void updateExposeById(Long noticeId,
                        NoticeDto.UpdateExposeRequest updateExpose);

  void deleteById(Long noticeId);

  void deleteNotices(NoticeDto.DeleteTotalNoticeRequest request);

//  Page<NoticeDto.ListNoticeResponse> getNotices(Pageable pageable,
//                                                NoticeDto.SearchRequest searchRequest);
}
