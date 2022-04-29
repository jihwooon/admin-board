package com.example.admin.demo.controller;

import com.example.admin.demo.application.NoticeService;
import com.example.admin.demo.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  //TODO : 공지사항 상세조회 기능 - 완료
  @GetMapping("/notice/{noticeId}")
  @ResponseStatus(HttpStatus.OK)
  public NoticeDto.getNoticeByIdResponse getNoticeById(@PathVariable final Long noticeId) {
    return NoticeDto.getNoticeByIdResponse.of(noticeService.getNoticeById(noticeId));
  }

  //TODO : 공지사항 수정 기능 -
  @PutMapping("/notice/{noticeId}")
  @ResponseStatus(HttpStatus.OK)
  public void updateNotice(@PathVariable final Long noticeId,
                           @RequestBody @Valid final NoticeDto.updateNoticeRequest request) {
    noticeService.updateNotice(noticeId, request);
  }

  //TODO : 공지사항 선택 삭제 기능 - 완료
  @DeleteMapping("notice/{noticeId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteNotice(@PathVariable final Long noticeId) {
    noticeService.deleteById(noticeId);
  }

  //TODO : 공지사항 전체 삭제 기능 - 완료
  @DeleteMapping("/notice")
  @ResponseStatus(HttpStatus.OK)
  public void deleteNotices(@RequestBody final NoticeDto.DeleteTotalNoticeRequest request) {
    noticeService.deleteNotices(request);
  }
}
