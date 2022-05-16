package com.example.admin.demo.event.domain;

import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.common.enums.ColorType;
import com.example.admin.demo.event.dto.EventDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

  private Event event;

  @Test
  void creation() {
    event = Event.builder()
        .eventTitle("이벤트 제목")
        .eventSubTitle("이벤트 서브 제목")
        .eventStart(LocalDateTime.parse("2021-05-11T00:00"))
        .eventEnd(LocalDateTime.parse("2021-01-30T00:00"))
        .repImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg")
        .imageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg")
        .expose(false)
        .enable(true)
        .colorType(ColorType.BLACK)
        .build();

    assertThat(event.getEventTitle()).isEqualTo("이벤트 제목");
    assertThat(event.getEventSubTitle()).isEqualTo("이벤트 서브 제목");
    assertThat(event.getEventStart()).isEqualTo(LocalDateTime.parse("2021-05-11T00:00"));
    assertThat(event.getEventEnd()).isEqualTo(LocalDateTime.parse("2021-01-30T00:00"));
    assertThat(event.getRepImageUrl()).isEqualTo("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    assertThat(event.getImageUrl()).isEqualTo("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    assertThat(event.isExpose()).isFalse();
    assertThat(event.isEnable()).isTrue();
    assertThat(event.getColorType()).isEqualTo(ColorType.BLACK);
  }

  @Test
  void changeEvent() {
    EventDto.UpdateEventRequest updateRequest = new EventDto.UpdateEventRequest();
    updateRequest.setEventTitle("이벤트 수정 타이틀");
    updateRequest.setEventSubTitle("이벤트 수정 서브 타이틀");
    updateRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    updateRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
    updateRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    updateRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    updateRequest.setColorText(ColorType.BLACK);

    event = Event.builder().build();

    event.changeEvent(updateRequest);

    assertThat(event.getEventTitle()).isEqualTo("이벤트 수정 타이틀");
    assertThat(event.getEventSubTitle()).isEqualTo("이벤트 수정 서브 타이틀");
    assertThat(event.getEventStart()).isEqualTo(LocalDateTime.parse("2021-05-11T00:00"));
    assertThat(event.getEventEnd()).isEqualTo(LocalDateTime.parse("2021-01-30T00:00"));
    assertThat(event.getRepImageUrl()).isEqualTo("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    assertThat(event.getImageUrl()).isEqualTo("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");

  }

  @Test
  void EnableIsFalse() {
    event = Event.builder()
        .enable(false)
        .build();

    event.changeEnable(false);

    assertThat(event.isEnable()).isFalse();
  }

  @Test
  void EnableIsTrue() {
    event = Event.builder()
        .enable(true)
        .build();

    event.changeEnable(true);

    assertThat(event.isEnable()).isTrue();
  }

  @Test
  void EnableIsTrueAndChangeIsFalse() {
    event = Event.builder()
        .enable(true)
        .build();

    event.changeEnable(false);

    assertThat(event.isEnable()).isFalse();
  }

  @Test
  void EnableIsFalseAndChangeIsTrue() {
    event = Event.builder()
        .enable(false)
        .build();

    event.changeEnable(true);

    assertThat(event.isEnable()).isTrue();
  }

  @Test
  void changeExpose() {
    event = Event.builder()
        .expose(false)
        .build();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    event.changeExpose(updateExposeRequest);

    assertThat(event.isExpose()).isTrue();
  }

  @Test
  void changeExposeIsFalse() {
    event = Event.builder()
        .expose(false)
        .build();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    event.changeExpose(updateExposeRequest);

    assertThat(event.isExpose()).isFalse();
  }

  @Test
  void changeExposeIsTrue() {
    event = Event.builder()
        .expose(true)
        .build();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    event.changeExpose(updateExposeRequest);

    assertThat(event.isExpose()).isTrue();
  }

  @Test
  void changeExposeEventIsTrueAndExposeRequestIsFalse() {
    event = Event.builder()
        .expose(true)
        .build();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    event.changeExpose(updateExposeRequest);

    assertThat(event.isExpose()).isFalse();
  }
}
