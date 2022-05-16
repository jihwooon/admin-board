package com.example.admin.demo.event.application.impl;

import com.example.admin.demo.common.enums.ColorType;
import com.example.admin.demo.common.error.EventNotFoundException;
import com.example.admin.demo.event.domain.Event;
import com.example.admin.demo.event.dto.EventDto;
import com.example.admin.demo.event.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EventServiceImplTest {
  private EventServiceImpl eventService;

  private EventRepository eventRepository = mock(EventRepository.class);

  private Event event;
  private Event eventId;

  @BeforeEach
  void setUp() {

    eventService = new EventServiceImpl(eventRepository);
    event = Event.builder()
        .eventTitle("이벤트제목")
        .eventSubTitle("이벤트 부제목")
        .repImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg")
        .imageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg")
        .eventStart(LocalDateTime.parse("2021-05-11T00:00"))
        .eventEnd(LocalDateTime.parse("2021-05-30T00:00"))
        .colorType(ColorType.WHITE)
        .build();

    eventId = eventRepository.save(event);

    given(eventRepository.save(any(Event.class))).willReturn(event);

    given(eventRepository.findById(1L)).willReturn(Optional.of(event));

    given(eventRepository.findById(100L)).willReturn(Optional.empty());

    given(eventRepository.save(any(Event.class))).will(invocation -> {
      Event source = invocation.getArgument(0);
      return Event.builder()
          .eventTitle(source.getEventTitle())
          .eventSubTitle(source.getEventSubTitle())
          .eventStart(source.getEventStart())
          .eventEnd(source.getEventEnd())
          .repImageUrl(source.getRepImageUrl())
          .imageUrl(source.getImageUrl())
          .colorType(source.getColorType())
          .build();
    });
  }

  @Test
  void assertThatEqual() {
    assertThat(event.getEventTitle()).isEqualTo("이벤트제목");
    assertThat(event.getEventSubTitle()).isEqualTo("이벤트 부제목");
    assertThat(event.getEventStart()).isEqualTo(LocalDateTime.parse("2021-05-11T00:00"));
    assertThat(event.getEventEnd()).isEqualTo(LocalDateTime.parse("2021-05-30T00:00"));
  }


  @Test
  void getEventById() {
    Event eventById = eventService.getEventById(1L);

    assertThat(eventById).isNotNull();

    verify(eventRepository).findById(1L);
  }

  @Test
  void getEventByNotFoundId() {
    assertThatThrownBy(() -> eventService.getEventById(1000L))
        .isInstanceOf(EventNotFoundException.class);
  }

  @Test
  void createEvent() {
    //given
    EventDto.CreateEventRequest createRequest = new EventDto.CreateEventRequest();
    createRequest.setEventTitle("이벤트제목");
    createRequest.setEventSubTitle("이벤트 부제목");
    createRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    createRequest.setEventEnd(LocalDateTime.parse("2021-05-30T00:00"));
    createRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    createRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    createRequest.setColorText(ColorType.WHITE);

    Long eventId = eventRepository.save(event).getEventId();

    verify(eventRepository, times(2)).save(event);
  }

  @Test
  void updateEvent() {
    EventDto.UpdateEventRequest updateRequest = new EventDto.UpdateEventRequest();
    updateRequest.setEventTitle("이벤트 수정 타이틀");
    updateRequest.setEventSubTitle("이벤트 수정 서브 타이틀");
    updateRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    updateRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
    updateRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    updateRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    updateRequest.setColorText(ColorType.BLACK);

    eventService.updateEvent(1L, updateRequest);

    verify(eventRepository, times(2)).save(event);

  }

//  @Test
//  void searchEvent() {
//    EventDto.SearchRequest searchRequest = new EventDto.SearchRequest();
//    searchRequest.setEventTitle("검색 제목");
//    searchRequest.setEventStart(LocalDateTime.parse("2022-05-01T00:00:00"));
//    searchRequest.setEventEnd(LocalDateTime.parse("2022-05-31T00:00:00"));
//
//    int size = 10;
//    int page = 5;
//
//    CommonDto.PageResponse events = eventService.getEvents(PageRequest.of(size, page), searchRequest);
//
//    assertThat(events.getSize()).isLessThan(10);
//
//  }

}
