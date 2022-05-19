package com.example.admin.demo.event.application.impl;

import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.common.error.EventNotFoundException;
import com.example.admin.demo.event.domain.Event;
import com.example.admin.demo.event.dto.EventDto;
import com.example.admin.demo.event.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = "spring.profiles.active:integration")
@AutoConfigureMockMvc
class EventServiceImplTest {

  @Autowired
  private EventServiceImpl eventService;

  @Autowired
  private EventRepository eventRepository;

  private EventDto.CreateEventRequest createRequest;

  private EventDto.UpdateEventRequest updateRequest;

  private CommonDto.UpdateExposeRequest updateExposeRequest;

  private Long eventId;

  @BeforeEach
  void setUp() {
    createRequest = new EventDto.CreateEventRequest();
    createRequest.setEventTitle("이벤트제목");
    createRequest.setEventSubTitle("이벤트 부제목");

    updateRequest = new EventDto.UpdateEventRequest();
    updateRequest.setEventTitle("이벤트 수정 타이틀");
    updateRequest.setEventSubTitle("이벤트 수정 서브 타이틀");

    updateExposeRequest = new CommonDto.UpdateExposeRequest();

    eventId = eventService.createEvent(createRequest);

  }

  @Test
  void getEventById() {
    Event event = eventRepository.findById(eventId).get();

    assertThat(event.getEventTitle()).isEqualTo("이벤트제목");
    assertThat(event.getEventSubTitle()).isEqualTo("이벤트 부제목");
  }

  @Test
  void getEventByNotFoundId() {
    assertThatThrownBy(() -> eventService.getEventById(1000L))
        .isInstanceOf(EventNotFoundException.class);
  }

  @Test
  void updateEvent() {
    eventService.updateEvent(eventId, updateRequest);
    Event event = eventRepository.findById(eventId).get();

    assertThat(event.getEventTitle()).isEqualTo(updateRequest.getEventTitle());
    assertThat(event.getEventSubTitle()).isEqualTo(updateRequest.getEventSubTitle());

  }

  @Test
  void exposeEventIsTrue() {
    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    eventId = eventRepository.save(Event.builder()
        .expose(true)
        .build())
        .getEventId();

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isTrue();
  }

  @Test
  void exposeEventIsFalse() {
    eventId = eventRepository.save(Event.builder()
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
    eventId = eventRepository.save(Event.builder()
        .expose(true)
        .build())
        .getEventId();

    updateExposeRequest.setExpose(false);

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isFalse();
  }

  @Test
  void exposeEventIsFalseAndTrue() {
    eventId = eventRepository.save(Event.builder()
        .expose(false)
        .build())
        .getEventId();

    updateExposeRequest.setExpose(true);

    eventService.updateExposeById(eventId, updateExposeRequest);

    Event event = eventRepository.findById(eventId).get();

    assertThat(event.isExpose()).isTrue();
  }

  @Test
  void deleteById() {
    eventId = eventRepository.save(Event.builder()
        .enable(true)
        .build())
        .getEventId();

    eventService.deleteById(eventId);

    Optional<Event> eventEnable = eventRepository.findByEventIdAndEnableIsTrue(eventId);

    assertThat(eventEnable.isPresent()).isFalse();
  }

  @Test
  void deletesEvent() {
    Long eventId1 = eventRepository.save(Event.builder()
        .enable(true)
        .build()).getEventId();

    Long eventId2 = eventRepository.save(Event.builder()
        .enable(true)
        .build()).getEventId();

    Long eventId3 = eventRepository.save(Event.builder()
        .enable(true)
        .build()).getEventId();

    List<Long> events = Arrays.asList(eventId1, eventId2, eventId3);
    List<Event> eventIds = eventRepository.findByEventIdInAndEnableIsTrue(events);

    System.out.println("events =-=-=-=-=-=-=-=-=-=-= " + eventIds);

    for (Event event : eventIds) {
      event.changeEnable(false);
    }

    List<Event> result = eventRepository.saveAll(eventIds);

    System.out.println("result =-=-=-=-=-=-=-=-=-" + result);

    assertThat(eventIds).isEqualTo(result);
    assertThat(result).extracting("enable").contains(false);
    assertThat(eventIds).extracting("enable").contains(false);

  }

  @Test
  void deletesEventWithNotExisted() {
    Long eventId1 = eventRepository.save(Event.builder()
        .enable(true)
        .build()).getEventId();

    Long eventId2 = eventRepository.save(Event.builder()
        .enable(true)
        .build()).getEventId();

    List<Long> ARRAYS_LIST = Arrays.asList(eventId1, eventId2);

    EventDto.DeleteEventRequest deleteEventRequest = new EventDto.DeleteEventRequest();
    deleteEventRequest.setEventIds(ARRAYS_LIST);

    eventService.deletesEvent(deleteEventRequest);

    assertThat(deleteEventRequest).extracting("eventIds");

  }

  @Test
  void mockingTest() {
    CommonDto.UpdateExposeRequest updateExposeRequest = new CommonDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    List<Long> ARRAYS_LIST = Arrays.asList(eventId);

    EventDto.DeleteEventRequest deleteEventRequest = new EventDto.DeleteEventRequest();
    deleteEventRequest.setEventIds(ARRAYS_LIST);

    eventService = mock(EventServiceImpl.class);

    eventService.createEvent(createRequest);
    eventService.updateEvent(eventId, updateRequest);
    eventService.getEventById(eventId);
    eventService.updateExposeById(eventId, updateExposeRequest);
    eventService.deletesEvent(deleteEventRequest);

    verify(eventService).createEvent(createRequest);
    verify(eventService).updateEvent(eventId, updateRequest);
    verify(eventService).getEventById(eventId);
    verify(eventService).updateExposeById(eventId, updateExposeRequest);
    verify(eventService).deletesEvent(deleteEventRequest);
  }

  @Test
  void equalTest() {
    Event event1 = Event.builder().eventTitle("제목").build();
    Event event2 = Event.builder().eventTitle("제목").build();

    assertThat(event1).isNotEqualTo(event2);
  }

}
