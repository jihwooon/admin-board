package com.example.admin.demo.application.Impl;

import com.example.admin.demo.application.EventService;
import com.example.admin.demo.domain.event.Event;
import com.example.admin.demo.dto.EventDto;
import com.example.admin.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        .colorType(createEventRequest.getColorText())
        .statusType(createEventRequest.getStatusType())
        .build();

    eventRepository.save(event);
  }
}
