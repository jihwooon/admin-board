package com.example.admin.demo.repository;

import com.example.admin.demo.dto.CommonDto;
import com.example.admin.demo.dto.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepositoryCustom {

  Page<CommonDto.PageResponse> getEventByCondition(Pageable pageable,
                                                   EventDto.SearchRequest searchRequest);

}
