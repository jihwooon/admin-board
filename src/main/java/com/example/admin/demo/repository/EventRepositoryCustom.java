package com.example.admin.demo.repository;

import com.example.admin.demo.dto.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepositoryCustom {

  Page<EventDto.SearchResultResponse> getEventByCondition(Pageable pageable,
                                                          EventDto.SearchRequest searchRequest);

}
