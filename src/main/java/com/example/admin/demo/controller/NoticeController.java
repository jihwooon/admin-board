package com.example.admin.demo.controller;

import com.example.admin.demo.application.NoticeService;
import com.example.admin.demo.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeController {

  private final NoticeService noticeService;

  @PostMapping("/notice")
  @ResponseStatus(HttpStatus.CREATED)
  public void createNotice(@RequestBody @Valid final NoticeDto.CreateNoticeRequest request) {
    noticeService.createNotice(request);
  }

}
