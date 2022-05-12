package com.example.admin.demo.notice.controller;

import com.example.admin.demo.notice.application.NoticeService;
import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public Long createNotice(@RequestBody @Valid final NoticeDto.CreateNoticeRequest createNotice) {
    return noticeService.createNotice(createNotice);
  }

  @GetMapping("/notice")
  @ResponseStatus(HttpStatus.OK)
  public CommonDto.PageResponse getNotices(@RequestParam(value = "page", defaultValue = "0") final int page,
                                           @RequestParam(value = "size", defaultValue = "10") final int size,
                                           @ModelAttribute final NoticeDto.SearchRequest searchRequest) {
    return noticeService.getNotices(PageRequest.of(page, size), searchRequest);
  }

  @GetMapping("/notice/{noticeId}")
  @ResponseStatus(HttpStatus.OK)
  public NoticeDto.getNoticeByIdResponse getNoticeById(@PathVariable final Long noticeId) {
    return NoticeDto.getNoticeByIdResponse.of(noticeService.getNoticeById(noticeId));
  }

  @PutMapping("/notice/{noticeId}")
  @ResponseStatus(HttpStatus.OK)
  public void updateNotice(@PathVariable final Long noticeId,
                           @RequestBody @Valid final NoticeDto.UpdateNoticeRequest updateNotice) {
    noticeService.updateNotice(noticeId, updateNotice);
  }

  @PutMapping("/notice/{noticeId}/expose")
  @ResponseStatus(HttpStatus.OK)
  public void updateExposeById(@PathVariable final Long noticeId,
                               @RequestBody @Valid final NoticeDto.UpdateExposeRequest updateExpose) {
    noticeService.updateExposeById(noticeId, updateExpose);
  }

  @DeleteMapping("/notice/{noticeId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteNotice(@PathVariable final Long noticeId) {
    noticeService.deleteById(noticeId);
  }

  @DeleteMapping("/notice")
  @ResponseStatus(HttpStatus.OK)
  public void deleteNotices(@RequestBody final NoticeDto.DeleteTotalNoticeRequest deleteNotice) {
    noticeService.deleteNotices(deleteNotice);
  }
}
