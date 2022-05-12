package com.example.admin.demo.notice.application;


import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.notice.domain.Notice;
import com.example.admin.demo.notice.dto.NoticeDto;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

  Long createNotice(NoticeDto.CreateNoticeRequest request);

  Notice getNoticeById(Long noticeId);

  void updateNotice(Long noticeId,
                    NoticeDto.UpdateNoticeRequest request);

  void updateExposeById(Long noticeId,
                        NoticeDto.UpdateExposeRequest updateExpose);

  void deleteById(Long noticeId);

  void deleteNotices(NoticeDto.DeleteTotalNoticeRequest request);

  CommonDto.PageResponse getNotices(Pageable pageable,
                                    NoticeDto.SearchRequest searchRequest);

}
