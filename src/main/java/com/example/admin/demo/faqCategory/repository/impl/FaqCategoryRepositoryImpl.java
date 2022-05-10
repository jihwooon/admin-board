package com.example.admin.demo.faqCategory.repository.impl;

import com.example.admin.demo.event.dto.EventDto;
import com.example.admin.demo.faqCategory.domain.faqCategory.FaqCategory;
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

import java.util.ArrayList;
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
    List<Predicate> predicates = getPredicates(request);
    Predicate[] predicatesCondition = predicates.toArray(Predicate[]::new);

    List<FaqCategory> faqCategories = queryFactory.selectFrom(faqCategory)
        .where(predicatesCondition)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    return new PageImpl<>(FaqCategoryDto.SearchResultResponse.of(faqCategories), pageable, faqCategories.size());
  }

  private List<Predicate> getPredicates(final FaqCategoryDto.SearchRequest searchRequest) {
    return List.of (
            searchById(searchRequest.getFaqCategoryGroupId())
    );
  }


  private BooleanExpression searchById(final Long faqCategoryId) {

    if(!ObjectUtils.isEmpty(faqCategoryId)) {
      return faqCategory.id.eq(faqCategoryId);
    }
    return null;
  }
}
