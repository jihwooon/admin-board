package com.example.admin.demo.controller;

import com.example.admin.demo.application.NoticeService;
import com.example.admin.demo.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  //TODO : 공지사항 등록 기능 - 완료
  @PostMapping("/notice")
  @ResponseStatus(HttpStatus.CREATED)
  public void createNotice(@RequestBody @Valid final NoticeDto.CreateNoticeRequest request) {
    noticeService.createNotice(request);
  }

  //TODO : 공지사항 상세조회 기능
  @GetMapping("notice/{noticeId}")
  public NoticeDto.getNoticeResponse getNoticeById(@PathVariable final Long noticeId) {
    return NoticeDto.getNoticeResponse.of(noticeService.getNoticeById(noticeId));
  }

}
