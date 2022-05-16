package com.example.admin.demo.event.domain;

import com.example.admin.demo.common.BaseEntity;
import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.common.enums.ColorType;
import com.example.admin.demo.common.enums.StatusType;
import com.example.admin.demo.event.dto.EventDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long eventId;

  private String eventTitle;

  private String eventSubTitle;

  private LocalDateTime eventStart;

  private LocalDateTime eventEnd;

  private String repImageUrl;

  private String imageUrl;

  private boolean expose = false;

  private boolean enable = true;

  @Enumerated(value = EnumType.STRING)
  private StatusType statusType = StatusType.IN_PROGRESS;

  @Enumerated(value = EnumType.STRING)
  private ColorType colorType;

  @Builder
  public Event(final String eventTitle,
               final String eventSubTitle,
               final LocalDateTime eventStart,
               final LocalDateTime eventEnd,
               final String repImageUrl,
               final String imageUrl,
               final ColorType colorType,
               final boolean expose,
               final boolean enable) {

    this.eventTitle = eventTitle;
    this.eventSubTitle = eventSubTitle;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.repImageUrl = repImageUrl;
    this.imageUrl = imageUrl;
    this.colorType = colorType;
    this.expose = expose;
    this.enable = enable;
  }

  public void changeEvent(final EventDto.UpdateEventRequest updateEventRequest) {
    this.eventTitle = updateEventRequest.getEventTitle();
    this.eventSubTitle = updateEventRequest.getEventSubTitle();
    this.eventStart = updateEventRequest.getEventStart();
    this.eventEnd = updateEventRequest.getEventEnd();
    this.repImageUrl = updateEventRequest.getRepImageUrl();
    this.imageUrl = updateEventRequest.getImageUrl();
  }

  public void changeEnable(final boolean enable) {
    this.enable = enable;
  }

  public void changeExpose(final CommonDto.UpdateExposeRequest updateExposeRequest) {
    this.expose = updateExposeRequest.getExpose();
  }

}
