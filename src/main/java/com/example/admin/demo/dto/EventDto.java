package com.example.admin.demo.dto;

import com.example.admin.demo.domain.enums.ColorType;
import com.example.admin.demo.domain.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
}
