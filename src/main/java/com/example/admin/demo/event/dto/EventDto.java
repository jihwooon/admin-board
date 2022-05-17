package com.example.admin.demo.event.dto;

import com.example.admin.demo.common.enums.ColorType;
import com.example.admin.demo.common.enums.EventOrder;
import com.example.admin.demo.common.enums.StatusType;
import com.example.admin.demo.event.domain.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventDto {

  @Getter
  @Setter
  public static class CreateEventRequest {

    @NotEmpty(message = "띄어쓰기 포함 최대 23자 입력 가능합니다.")
    @Size(max = 20)
    private String eventTitle;

    @NotEmpty(message = "띄어쓰기 포함 최대 35자 입력 가능합니다.")
    @Size(max = 30)
    private String eventSubTitle;

    @NotEmpty
    private String repImageUrl;

    @NotEmpty
    private String imageUrl;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime eventStart;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime eventEnd;

    @NotNull(message = "컬러는 흰색/검은색 둘 중 하나를 필수로 선택합니다.")
    private ColorType colorText;

    public Event toEntity() {
      return Event.builder()
          .eventTitle(this.eventTitle)
          .eventSubTitle(this.eventSubTitle)
          .eventStart(this.eventStart)
          .eventEnd(this.eventEnd)
          .repImageUrl(this.repImageUrl)
          .imageUrl(this.imageUrl)
          .colorType(this.colorText)
          .build();
    }

  }

  @Getter
  public static class getEventByIdResponse {

    private String repImageUrl;

    private String imageUrl;

    private LocalDateTime eventStart;

    private LocalDateTime eventEnd;

    private Boolean expose;

    private String eventTitle;

    private String eventSubTitle;

    private ColorType colorText;


    public getEventByIdResponse(final Event event) {
      this.repImageUrl = event.getRepImageUrl();
      this.imageUrl = event.getImageUrl();
      this.eventStart = event.getEventStart();
      this.eventEnd = event.getEventEnd();
      this.expose = event.isExpose();
      this.eventTitle = event.getEventTitle();
      this.eventSubTitle = event.getEventSubTitle();
      this.colorText = event.getColorType();
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

    @NotNull
    private LocalDateTime eventStart;

    @NotNull
    private LocalDateTime eventEnd;


    @NotBlank(message = "띄어쓰기 포함 최대 23자 입력 가능합니다.")
    @Size(max = 20)
    private String eventTitle;

    @NotBlank(message = "띄어쓰기 포함 최대 35자 입력 가능합니다.")
    @Size(max = 30)
    private String eventSubTitle;

    @NotNull(message = "컬러는 흰색/검은색 둘 중 하나를 필수로 선택합니다.")
    private ColorType colorText;

  }

  @Getter
  @Setter
  public static class DeleteEventRequest {

    @NotEmpty
    private List<Long> eventIds = new ArrayList<>();
  }

  @Getter
  @Builder
  public static class SearchRequest {
    private String eventTitle;
    private EventOrder eventOrder;
    private List<StatusType> statusTypes;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

  }

  @Getter
  public static class SearchResultResponse {
    private Long eventId;
    private String eventTitle;
    private StatusType statusType;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private LocalDateTime createTime;
    private Boolean expose;

    public SearchResultResponse(final Event event) {
      this.eventId = event.getEventId();
      this.eventTitle = event.getEventTitle();
      this.statusType = event.getStatusType();
      this.eventStart = event.getEventStart();
      this.eventEnd = event.getEventEnd();
      this.createTime = event.getCreateTime();
      this.expose = event.isExpose();
    }

//    public static SearchResultResponse of(final Event event) {
//      return new SearchResultResponse(event);
//    }

    public static List<SearchResultResponse> of(final List<Event> events) {
      return events.stream()
          .map(o -> new SearchResultResponse(o))
          .collect(Collectors.toList());
    }
  }
}
