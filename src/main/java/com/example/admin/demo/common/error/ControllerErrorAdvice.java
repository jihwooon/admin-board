package com.example.admin.demo.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerErrorAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EventNotFoundException.class)
  public void handleEventNotFoundException() {

  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoticeNotFoundException.class)
  public void handleNoticeNotFoundException() {

  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(FaqCategoryNotFoundException.class)
  public void handleCategoryNotFoundException() {

  }


}
