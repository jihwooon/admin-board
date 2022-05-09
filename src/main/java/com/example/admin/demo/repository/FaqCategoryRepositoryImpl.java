package com.example.admin.demo.repository;

import com.example.admin.demo.domain.faqCategory.FaqCategory;
import com.example.admin.demo.dto.FaqCategoryDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.example.admin.demo.domain.faqCategory.QFaqCategory.faqCategory;

@Repository
public class FaqCategoryRepositoryImpl extends QuerydslRepositorySupport implements FaqCategoryRepositoryCustom{

  private final JPAQueryFactory queryFactory;

  public FaqCategoryRepositoryImpl(JPAQueryFactory queryFactory) {
    super(FaqCategory.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<FaqCategoryDto.SearchResultResponse> getFaqCategoryByCondition(final Pageable pageable,
                                                                             final FaqCategoryDto.SearchRequest request) {
    List<FaqCategory> faqCategories = queryFactory.selectFrom(faqCategory)
        .where(searchById(request.getFaqCategoryGroupId()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    return new PageImpl<>(FaqCategoryDto.SearchResultResponse.of(faqCategories), pageable, faqCategories.size());
  }

  private BooleanExpression searchById(final Long faqCategoryId) {

    if(!ObjectUtils.isEmpty(faqCategoryId)) {
      return faqCategory.id.eq(faqCategoryId);
    }
    return null;
  }
}
