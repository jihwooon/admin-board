package com.example.admin.demo.notice.repository.Impl;

import com.example.admin.demo.notice.domain.Notice;
import com.example.admin.demo.notice.dto.NoticeDto;
import com.example.admin.demo.notice.repository.NoticeRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
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

    private JPAQueryFactory jpaQueryFactory;

    public NoticeRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Notice.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<NoticeDto.SearchResultNoticeResponse> findNoticeByCondition(final Pageable pageable,
                                                                            final NoticeDto.SearchRequest searchRequest) {
        List<Predicate> predicates = getPredicates(searchRequest);
        Predicate[] predicatesCondition = predicates.toArray(Predicate[]::new);

        long totalCount = getTotalCount(searchRequest);

        List<Notice> notices = jpaQueryFactory.selectFrom(notice)
                .where(predicatesCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(NoticeDto.SearchResultNoticeResponse.of(notices), pageable, totalCount);
    }

    private List<Predicate> getPredicates(final NoticeDto.SearchRequest searchRequest) {
        return List.of(
                searchByTitle(searchRequest.getNoticeTitle())
        );
    }

    private Long getTotalCount(NoticeDto.SearchRequest searchRequest) {
        return jpaQueryFactory.select(Wildcard.count)
                .from(notice)
                .where(searchByTitle(searchRequest.getNoticeTitle()))
                .fetch().get(0);
    }

    private BooleanExpression searchByTitle(final String NoticeTitle) {

        if (!ObjectUtils.isEmpty(NoticeTitle)) {
            return notice.noticeTitle.eq(NoticeTitle);
        }
        return null;
    }
}
