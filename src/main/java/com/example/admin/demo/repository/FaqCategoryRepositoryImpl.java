package com.example.admin.demo.repository;

import com.example.admin.demo.dto.FaqCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class FaqCategoryRepositoryImpl implements FaqCategoryRepositoryCustom {

  private final EntityManager em;

  @Override
  public List<FaqCategoryDto.ListFaqCategoryResponse> findAllFaqCategoryBy(FaqCategoryDto.SearchConditionRequestDto request, Pageable pageable) {
    return em.createQuery("select r from ListFaqCategoryResponse r", FaqCategoryDto.ListFaqCategoryResponse.class)
        .setFirstResult(10)
        .setMaxResults(20)
        .getResultList();
  }
}
