package com.example.admin.demo.error;

public class NoticeNotFoundException extends RuntimeException {

  public NoticeNotFoundException(String message) {
    super(message);
  }
}
