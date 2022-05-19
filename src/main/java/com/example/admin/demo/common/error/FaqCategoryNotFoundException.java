package com.example.admin.demo.common.error;

public class FaqCategoryNotFoundException extends RuntimeException {

  public FaqCategoryNotFoundException(Long id) {
    super("Not Found : " + id);
  }

  public FaqCategoryNotFoundException(String message) {
    super(message);
  }
}
