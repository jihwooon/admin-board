package com.example.admin.demo.repository;


import com.example.admin.demo.domain.enums.StatusType;
import com.example.admin.demo.domain.event.Event;
import com.example.admin.demo.dto.CommonDto;
import com.example.admin.demo.dto.EventDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.admin.demo.domain.event.QEvent.event;

@Repository
public class EventRepositoryImpl extends QuerydslRepositorySupport implements EventRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public EventRepositoryImpl(JPAQueryFactory queryFactory) {
    super(Event.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<CommonDto.PageResponse> getEventByCondition(final Pageable pageable,
                                                          final EventDto.SearchRequest searchRequest) {
    List<Event> events = queryFactory.selectFrom(event)
        .where(
            searchByEnable(),
            searchByTitle(searchRequest.getEventTitle()),
            searchByEventStatuses(searchRequest.getStatusTypes()),
            searchByCreated(searchRequest.getEventStart(),searchRequest.getEventEnd())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        //.orderBy()
        .fetch();
    return new PageImpl<>(CommonDto.PageResponse.of(events.));
  }

  private BooleanExpression searchByCreated(final LocalDateTime eventStart,
                                            final LocalDateTime eventEnd) {
    if(!ObjectUtils.isEmpty(eventStart) && !ObjectUtils.isEmpty(eventEnd)) {
      return event.createTime.between(eventStart, eventEnd);
    }
    return null;
  }

  private BooleanExpression searchByEventStatuses(final List<StatusType> statusTypes) {

    if(!ObjectUtils.isEmpty(statusTypes)) {
      return event.statusType.in(statusTypes);
    }
    return null;
  }

  private BooleanExpression searchByTitle(final String eventTitle) {

    if(!ObjectUtils.isEmpty(eventTitle)) {
      return event.eventTitle.eq(eventTitle);
    }
    return null;
  }

  private BooleanExpression searchByEnable() {
    return event.enable.isTrue();
  }
}
