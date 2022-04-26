package com.example.admin.demo.application.error;

public class FaqCategoryGroupIdNotFoundException extends RuntimeException {
  public FaqCategoryGroupIdNotFoundException(Long faqCategoryGroupId) {
    super("Not Found : "+ faqCategoryGroupId);
  }
}
