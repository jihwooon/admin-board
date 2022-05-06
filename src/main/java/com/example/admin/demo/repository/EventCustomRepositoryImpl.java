package com.example.admin.demo.repository;

import com.example.admin.demo.domain.event.Event;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class EventCustomRepositoryImpl extends QuerydslRepositorySupport implements EventCustomRepository {

  private final JPAQueryFactory queryFactory;

  public EventCustomRepositoryImpl(JPAQueryFactory queryFactory) {
    super(Event.class);
    this.queryFactory = queryFactory;
  }

}
