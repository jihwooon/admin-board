package com.example.admin.demo.common;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDsl {
  private static JPAQueryFactory jpaQueryFactory;

  public CommonDsl(JPAQueryFactory queryFactory) {
    this.jpaQueryFactory = queryFactory;
  }
  public static Long getTotalCount(final Predicate[] predicatesCondition,
                                   final EntityPath<?> args) {

      return jpaQueryFactory.select(Wildcard.count)
          .from(args)
          .where(predicatesCondition)
          .fetch().get(0);
    }
}
