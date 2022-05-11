package com.example.admin.demo.notice.repository.Impl;

import com.example.admin.demo.common.CommentDsl;
import com.example.admin.demo.notice.domain.Notice;
import com.example.admin.demo.notice.dto.NoticeDto;
import com.example.admin.demo.notice.repository.NoticeRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.example.admin.demo.faqCategory.domain.notice.QNotice.notice;

@Repository
public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  public NoticeRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    super(Notice.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public Page<NoticeDto.SearchResultNoticeResponse> findNoticeByCondition(final Pageable pageable,
                                                                          final NoticeDto.SearchRequest searchRequest) {
//    List<Predicate> predicates = List.of ();
//    predicates.toArray(Predicate[]::new);

    Predicate[] predicatesCondition = getPredicates(searchRequest);

    List<Notice> notices = jpaQueryFactory.selectFrom(notice)
        .where()
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = CommentDsl.getTotalCount(predicatesCondition, notice);

    return new PageImpl<>(NoticeDto.SearchResultNoticeResponse.of(notices), pageable, totalCount);
  }

  private Predicate[] getPredicates(NoticeDto.SearchRequest searchRequest) {
    return new Predicate[]{searchByTitle(searchRequest.getNoticeTitle())};
  }

  private BooleanExpression searchByTitle(final String NoticeTitle) {

    if (!ObjectUtils.isEmpty(NoticeTitle)) {
      return notice.noticeTitle.eq(NoticeTitle);
    }
    return null;
  }

}
