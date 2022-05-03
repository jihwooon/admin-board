package com.example.admin.demo.dto;

import com.example.admin.demo.domain.enums.ColorType;
import com.example.admin.demo.domain.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private String eventTitle;

    @NotEmpty(message = "띄어쓰기 포함 최대 35자 입력 가능합니다.")
    private String eventSubTitle;

    @NotNull
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

    //TODO  : eventStart / eventEnd LocalDate Json
    @NotEmpty
    private String eventStart;

    @NotEmpty
    private String eventEnd;

    @NotNull
    private Boolean expose;

    @NotEmpty(message = "띄어쓰기 포함 최대 23자 입력 가능합니다.")
    private String eventTitle;

    @NotEmpty(message = "띄어쓰기 포함 최대 35자 입력 가능합니다.")
    private String eventSubTitle;

    @NotNull
    private ColorType colorText;

    @NotNull
    private StatusType statusType;

  }

  @Getter
  public static class deleteEventResponse {
    private List<Long> events = new ArrayList<>();
  }
}

//TODO : 선택 삭제 기능
//TODO : 전체 삭제 기능
//TODO : 상세 보기 기능 Id 값에 따라 보기 기능
//TODO : Paging 기능 구현
//TODO : SearchDto 기능에 이벤트 제목, 이벤트 상태, 이벤트 등록일자 추가
