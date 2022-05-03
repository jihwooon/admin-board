package com.example.admin.demo.dto;

import com.example.admin.demo.domain.enums.ColorType;
import com.example.admin.demo.domain.enums.StatusType;
import com.example.admin.demo.domain.event.Event;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EventDto {

  @Getter
  @Setter
  public static class CreateEventRequest {

    @NotEmpty
    private String repImageUrl;

    @NotEmpty
    private String imageUrl;

    @NotEmpty
    private String eventStart;

    @NotEmpty
    private String eventEnd;

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
  @Setter
  public static class UpdateEventRequest {

    @NotEmpty
    private String repImageUrl;

    @NotEmpty
    private String imageUrl;

    @NotEmpty
    private String eventStart;

    @NotEmpty
    private String eventEnd;

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
  public static class DeleteEventRequest {

    @NotEmpty
    private List<Long> eventIds = new ArrayList<>();
  }

  @Getter
  public static class getEventByIdResponse {

    private String repImageUrl;

    private String imageUrl;

    private String eventStart;

    private String eventEnd;

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

    public static getEventByIdResponse of (final Event event) {
      return new getEventByIdResponse(event);
    }
  }
}


//TODO : 상세 보기 기능 Id 값에 따라 보기 기능
//TODO : Paging 기능 구현
//TODO : SearchDto 기능에 이벤트 제목, 이벤트 상태, 이벤트 등록일자 추가
