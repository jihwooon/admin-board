package com.example.admin.demo.common.error;

public class FaqCategoryGroupIdNotFoundException extends RuntimeException {
  public FaqCategoryGroupIdNotFoundException(Long faqCategoryGroupId) {
    super("Not Found : "+ faqCategoryGroupId);
  }
  public FaqCategoryGroupIdNotFoundException(String message) {
    super(message);
  }
}
