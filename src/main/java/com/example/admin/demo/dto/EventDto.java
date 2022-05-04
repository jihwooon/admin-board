package com.example.admin.demo.dto;

import com.example.admin.demo.domain.enums.ColorType;
import com.example.admin.demo.domain.enums.StatusType;
import com.example.admin.demo.domain.event.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventDto {

  @Getter
  @Setter
  public static class CreateEventRequest {

    @NotEmpty
    private String repImageUrl;

    @NotEmpty
    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventEnd;

    @NotNull
    private Boolean expose;

    @NotEmpty(message = "띄어쓰기 포함 최대 23자 입력 가능합니다.")
    @Size(max = 20)
    private String eventTitle;

    @NotEmpty(message = "띄어쓰기 포함 최대 35자 입력 가능합니다.")
    @Size(max = 30)
    private String eventSubTitle;

    @NotNull(message = "컬러는 흰색/검은색 둘 중 하나를 필수로 선택합니다.")
    private ColorType colorText;

    @NotNull
    private StatusType statusType;
  }

  @Getter
  public static class getEventByIdResponse {

    private String repImageUrl;

    private String imageUrl;

    private LocalDate eventStart;

    private LocalDate eventEnd;

    private Boolean expose;

    private String eventTitle;

    private String eventSubTitle;

    private ColorType colorText;

    private StatusType statusType;

    public getEventByIdResponse(final Event event) {
      this.repImageUrl = event.getRepImageUrl();
      this.imageUrl = event.getImageUrl();
      this.eventStart = event.getEventStart();
      this.eventEnd = event.getEventEnd();
      this.expose = event.isExpose();
      this.eventTitle = event.getEventTitle();
      this.eventSubTitle = event.getEventSubTitle();
      this.colorText = event.getColorType();
      this.statusType = event.getStatusType();
    }

    public static getEventByIdResponse of(final Event event) {
      return new getEventByIdResponse(event);
    }
  }

  @Getter
  @Setter
  public static class UpdateEventRequest {

    @NotEmpty
    private String repImageUrl;

    @NotEmpty
    private String imageUrl;

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventStart;

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventEnd;

    @NotNull
    private Boolean expose;

    @NotBlank(message = "띄어쓰기 포함 최대 23자 입력 가능합니다.")
    @Size(max = 20)
    private String eventTitle;

    @NotBlank(message = "띄어쓰기 포함 최대 35자 입력 가능합니다.")
    @Size(max = 30)
    private String eventSubTitle;

    @NotNull(message = "컬러는 흰색/검은색 둘 중 하나를 필수로 선택합니다.")
    private ColorType colorText;

    @NotNull
    private StatusType statusType;

  }

  @Getter
  public static class DeleteEventRequest {

    @NotEmpty
    private List<Long> eventIds = new ArrayList<>();
  }

  @Getter
  @Builder
  public static class SearchRequest {

    private String eventTitle;

    private StatusType statusType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventEnd;

    private LocalDateTime createTime;

  }

  @Getter
  public static class PageEventResponse {

    private long totalElements;
    private int totalPages;
    private List<EventDto.ListEventResponse> contents;

    public PageEventResponse(final Page<Event> events) {
      this.totalElements = events.getTotalElements();
      this.totalPages = events.getTotalPages();
      this.contents = ListEventResponse.of(events.getContent());
    }

    public static PageEventResponse of(final Page<Event> events) {
      return new PageEventResponse(events);
    }
  }

  @Getter
  public static class ListEventResponse {

    private Long eventId;

    private String eventTitle;

    private StatusType statusType;

    private LocalDate eventStart;

    private LocalDate eventEnd;

    private LocalDateTime createTime;

    private Boolean expose;

    public ListEventResponse(final Event event) {
      this.eventId = event.getEventId();
      this.eventTitle = event.getEventTitle();
      this.statusType = event.getStatusType();
      this.eventStart = event.getEventStart();
      this.eventEnd = event.getEventEnd();
      this.createTime = event.getCreateTime();
      this.expose = event.isExpose();
    }

    public static ListEventResponse of(final Event event) {
      return new ListEventResponse(event);
    }

    public static List<ListEventResponse> of(final List<Event> events) {
      return events.stream()
          .map(o -> new ListEventResponse(o))
          .collect(Collectors.toList());
    }
  }
}

//TODO : SearchDto 기능에 이벤트 제목, 이벤트 상태, 이벤트 등록일자 추가
