package com.example.admin.demo.event.repository.impl;


import com.example.admin.demo.common.enums.StatusType;
import com.example.admin.demo.event.domain.Event;
import com.example.admin.demo.event.dto.EventDto;
import com.example.admin.demo.event.repository.EventRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.admin.demo.event.QEvent.event;

@Repository
public class EventRepositoryImpl extends QuerydslRepositorySupport implements EventRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public EventRepositoryImpl(JPAQueryFactory queryFactory) {
    super(Event.class);
    this.queryFactory = queryFactory;
  }

  //TODO : querydsl 5.0 이상 부터는 fetchResult, fetchCount 사용을 권하지 않는다.
  /*
   * QueryResults로 count쿼리를 날리 것이 완벽하게 지원되지 않기 때문에, total count를 사용할 필요가 없다면, fetch()를 사용하라고 권한다.
   * total count가 필요하더라도, 안정성을 위해서는 fetch를 이용하고, count쿼리를 따로 날리는 방법이 좋을 것 같다.
   * */
  @Override
  public Page<EventDto.SearchResultResponse> getEventByCondition(final Pageable pageable,
                                                                 final EventDto.SearchRequest searchRequest) {

    List<Event> events = queryFactory.selectFrom(event)
        .where(
            searchByEnable(),
            searchByTitle(searchRequest.getEventTitle()),
            searchByEventStatuses(searchRequest.getStatusTypes()),
            searchByCreated(searchRequest.getEventStart(), searchRequest.getEventEnd())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(searchRequest.getEventOrder().getOrder())
        .fetch();

    long totalSize = queryFactory.select(Wildcard.count)
        .from(event)
        .where(
            searchByEnable(),
            searchByTitle(searchRequest.getEventTitle()),
            searchByEventStatuses(searchRequest.getStatusTypes()),
            searchByCreated(searchRequest.getEventStart(), searchRequest.getEventEnd()))
        .fetch().get(0);

    return new PageImpl<>(EventDto.SearchResultResponse.of(events), pageable, totalSize);
  }

  private BooleanExpression searchByCreated(final LocalDateTime eventStart,
                                            final LocalDateTime eventEnd) {

    if (ObjectUtils.isEmpty(eventStart) && ObjectUtils.isEmpty(eventEnd)) {
      return null;
    }

    return event.createTime.between(eventStart, eventEnd);
  }

  private BooleanExpression searchByEventStatuses(final List<StatusType> statusTypes) {

    if (ObjectUtils.isEmpty(statusTypes)) {
      return null; //all, null
    }
    return event.statusType.in(statusTypes);
  }

  private BooleanExpression searchByTitle(final String eventTitle) {

    if (ObjectUtils.isEmpty(eventTitle)) {
      return null;
    }
    return event.eventTitle.eq(eventTitle);
  }

  private BooleanExpression searchByEnable() {
    return event.enable.isTrue();
  }

}
