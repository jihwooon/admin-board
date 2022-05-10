package com.example.admin.demo.notice.repository;

import com.example.admin.demo.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

  Page<NoticeDto.SearchResultNoticeResponse> findNoticeByCondition(Pageable pageable,
                                                                   NoticeDto.SearchRequest searchRequest);
}
