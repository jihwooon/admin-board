package com.example.admin.demo.repository;

import com.example.admin.demo.domain.FaqCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> {
  Optional<FaqCategory> findByIdAndFaqCategory(Long id, FaqCategory faqCategory);
}
