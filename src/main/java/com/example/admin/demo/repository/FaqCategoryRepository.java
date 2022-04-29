package com.example.admin.demo.repository;

import com.example.admin.demo.domain.faqCategory.FaqCategory;
import com.example.admin.demo.domain.faqCategory.FaqCategoryGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> {

  Page<FaqCategory> findAllByFaqCategoryGroup(Pageable pageable, FaqCategoryGroup faqCategoryGroup);

}
