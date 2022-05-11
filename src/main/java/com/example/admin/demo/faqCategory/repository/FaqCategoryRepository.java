package com.example.admin.demo.faqCategory.repository;

import com.example.admin.demo.faqCategory.domain.FaqCategory;
import com.example.admin.demo.faqCategory.domain.FaqCategoryGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> , FaqCategoryRepositoryCustom{

  Page<FaqCategory> findAllByFaqCategoryGroup(Pageable pageable,
                                              FaqCategoryGroup faqCategoryGroup);

}
