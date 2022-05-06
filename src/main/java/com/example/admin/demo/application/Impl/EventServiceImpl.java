package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.EventService;
import com.example.admin.demo.domain.event.Event;
import com.example.admin.demo.dto.CommonDto;
import com.example.admin.demo.dto.EventDto;
import com.example.admin.demo.error.EventNotFoundException;
import com.example.admin.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  @Override
  public void createEvent(final EventDto.CreateEventRequest createEventRequest) {
    Event event = Event.builder()
        .repImageUrl(createEventRequest.getRepImageUrl())
        .imageUrl(createEventRequest.getImageUrl())
        .eventStart(createEventRequest.getEventStart())
        .eventEnd(createEventRequest.getEventEnd())
        .eventTitle(createEventRequest.getEventTitle())
        .eventSubTitle(createEventRequest.getEventSubTitle())
        .expose(createEventRequest.getExpose())
        .colorType(createEventRequest.getColorText())
        .build();

    eventRepository.save(event);
  }

  @Override
  public Event getEventById(final Long eventId) {
    return eventRepository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Id를 찾을 수 없습니다."));
  }

  @Override
  public CommonDto.PageResponse getEvents(final Pageable pageable,
                                          final EventDto.SearchRequest searchRequest) {

    if (searchRequest.getEventTitle().isEmpty()) {
      return CommonDto.PageResponse.of(eventRepository.findAll(pageable));
    } else {
      return CommonDto.PageResponse.of(eventRepository.findAllByEventTitleContaining(pageable, searchRequest.getEventTitle()));
    }
  }

  @Override
  public void updateEvent(final Long eventId,
                          final EventDto.UpdateEventRequest updateEventRequest) {
    Event event = getEventById(eventId);
    event.changeEvent(updateEventRequest);

    eventRepository.save(event);
  }

  @Override
  public void updateExposeById(final Long eventId,
                               final CommonDto.UpdateExposeRequest updateExposeRequest) {
    Event event = getEventById(eventId);
    event.changeExpose(updateExposeRequest);

    eventRepository.save(event);
  }


  @Override
  public void deleteById(final Long eventId) {
    Event event = deleteEventById(eventId);
    event.changeEnable(false);

    eventRepository.save(event);
  }

  @Override
  public void deletesEvent(final EventDto.DeleteEventRequest deleteEventRequest) {
    List<Event> events = eventRepository.findByEventIdInAndEnableIsTrue(deleteEventRequest.getEventIds());

    for (Event event : events) {
      event.changeEnable(false);
    }

    eventRepository.saveAll(events);
  }

  private Event deleteEventById(final Long eventId) {
    return eventRepository.findByEventIdAndEnableIsTrue(eventId)
        .orElseThrow(() -> new EventNotFoundException("Id를 찾을 수 없습니다."));
  }

}
