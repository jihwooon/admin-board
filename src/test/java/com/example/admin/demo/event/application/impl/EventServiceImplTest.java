package com.example.admin.demo.event.application.impl;

import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.common.error.EventNotFoundException;
import com.example.admin.demo.event.domain.Event;
import com.example.admin.demo.event.dto.EventDto;
import com.example.admin.demo.event.repository.EventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = "spring.profiles.active:integration")
@AutoConfigureMockMvc
class EventServiceImplTest {
  @Autowired
  private EventServiceImpl eventService;

  @Autowired
  private EventRepository eventRepository;

  @AfterEach
  void after() {
    eventRepository.deleteAllInBatch();
  }

  @Test
  void createEvent() {
    EventDto.CreateEventRequest createRequest = new EventDto.CreateEventRequest();
    createRequest.setEventTitle("이벤트제목");
    createRequest.setEventSubTitle("이벤트 부제목");

    Event event = eventRepository.save(Event.builder()
        .eventTitle(createRequest.getEventTitle())
        .eventSubTitle(createRequest.getEventSubTitle())
        .build());

    eventService.createEvent(createRequest);

    assertThat(createRequest.getEventTitle()).isEqualTo(event.getEventTitle());
    assertThat(createRequest.getEventSubTitle()).isEqualTo(event.getEventSubTitle());

  }

  @Test
  void getEventById() {
    Long eventId = eventRepository.save(Event.builder()
        .eventTitle("이벤트 제목")
        .eventSubTitle("이벤트 부제목")
        .build()).getEventId();

    eventService.getEventById(eventId);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.getEventTitle()).isEqualTo("이벤트 제목");
    assertThat(event.getEventSubTitle()).isEqualTo("이벤트 부제목");
  }

  @Test
  void getEventByNotFoundId() {
    assertThatThrownBy(() -> eventService.getEventById(1000L))
        .isInstanceOf(EventNotFoundException.class);
  }


  @Test
  void updateEvent() {
    Long eventId = eventRepository.save(Event.builder()
        .eventTitle("이벤트 제목")
        .eventSubTitle("이벤트 부제목")
        .build()).getEventId();

    EventDto.UpdateEventRequest updateRequest = new EventDto.UpdateEventRequest();
    updateRequest.setEventTitle("이벤트 수정 타이틀");
    updateRequest.setEventSubTitle("이벤트 수정 서브 타이틀");

    eventService.updateEvent(eventId, updateRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.getEventTitle()).isEqualTo(updateRequest.getEventTitle());
    assertThat(event.getEventSubTitle()).isEqualTo(updateRequest.getEventSubTitle());

  }

  @Test
  void exposeEventIsTrue() {
    Long eventId = eventRepository.save(Event.builder()
        .expose(true)
        .build())
        .getEventId();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isTrue();
  }

  @Test
  void exposeEventIsFalse() {
    Long eventId = eventRepository.save(Event.builder()
        .expose(false)
        .build())
        .getEventId();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isFalse();
  }

  @Test
  void exposeEventIsTrueAndFalse() {
    Long eventId = eventRepository.save(Event.builder()
        .expose(true)
        .build())
        .getEventId();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isFalse();
  }

  @Test
  void exposeEventIsFalseAndTrue() {
    Long eventId = eventRepository.save(Event.builder()
        .expose(false)
        .build())
        .getEventId();

    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isTrue();
  }

}
