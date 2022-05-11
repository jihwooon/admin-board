package com.example.admin.demo.faqCategory.repository.impl;

import com.example.admin.demo.common.CommentDsl;
import com.example.admin.demo.faqCategory.domain.FaqCategory;
import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import com.example.admin.demo.faqCategory.repository.FaqCategoryRepositoryCustom;
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

import static com.example.admin.demo.faqCategory.domain.faqCategory.QFaqCategory.faqCategory;

@Repository
public class FaqCategoryRepositoryImpl extends QuerydslRepositorySupport implements FaqCategoryRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public FaqCategoryRepositoryImpl(JPAQueryFactory queryFactory) {
    super(FaqCategory.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<FaqCategoryDto.SearchResultResponse> getFaqCategoryByCondition(final Pageable pageable,
                                                                             final FaqCategoryDto.SearchRequest request) {
    Predicate[] predicatesCondition = getPredicates(request);

    List<FaqCategory> faqCategories = queryFactory.selectFrom(faqCategory)
        .where(predicatesCondition)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long totalCount = CommentDsl.getTotalCount(predicatesCondition, faqCategory);

    return new PageImpl<>(FaqCategoryDto.SearchResultResponse.of(faqCategories), pageable, totalCount);
  }

  private Predicate[] getPredicates(final FaqCategoryDto.SearchRequest request) {
    return new Predicate[]{searchById(request.getFaqCategoryGroupId())};
  }

  private BooleanExpression searchById(final Long faqCategoryId) {

    if(!ObjectUtils.isEmpty(faqCategoryId)) {
      return faqCategory.id.eq(faqCategoryId);
    }
    return null;
  }
}
