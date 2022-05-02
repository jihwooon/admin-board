package com.example.admin.demo.domain.event;

import com.example.admin.demo.domain.common.BaseEntity;
import com.example.admin.demo.domain.enums.ColorType;
import com.example.admin.demo.domain.enums.StatusType;
import com.example.admin.demo.dto.EventDto;
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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long eventId;

  private String eventTitle;

  private String eventSubTitle;

  private String eventStart;

  private String eventEnd;

  private String repImageUrl;

  private String imageUrl;

  private boolean expose = false;

  private boolean enable = true;

  @Enumerated(value = EnumType.STRING)
  private StatusType statusType;

  @Enumerated(value = EnumType.STRING)
  private ColorType colorType;

  public void changeEnable(final boolean enable) {
    this.enable = enable;
  }

  @Builder
  public Event(final String eventTitle,
               final String eventSubTitle,
               final String eventStart,
               final String eventEnd,
               final String repImageUrl,
               final String imageUrl,
               final ColorType colorType,
               final StatusType statusType) {

    this.eventTitle = eventTitle;
    this.eventSubTitle = eventSubTitle;
    this.eventStart = eventStart;
    this.eventEnd = eventEnd;
    this.repImageUrl = repImageUrl;
    this.imageUrl = imageUrl;
    this.colorType = colorType;
    this.statusType = statusType;
  }

  public void changeEvent(final EventDto.UpdateEventRequest updateEventRequest) {
    this.eventTitle = updateEventRequest.getEventTitle();
    this.eventSubTitle = updateEventRequest.getEventSubTitle();
    this.eventStart = updateEventRequest.getEventStart();
    this.eventEnd = updateEventRequest.getEventEnd();
    this.repImageUrl = updateEventRequest.getRepImageUrl();
    this.imageUrl = updateEventRequest.getImageUrl();
  }
}
