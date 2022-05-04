package com.example.admin.demo.application;

import com.example.admin.demo.domain.event.Event;
import com.example.admin.demo.dto.CommonDto;
import com.example.admin.demo.dto.EventDto;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface EventService {

  void createEvent(EventDto.CreateEventRequest createEventRequestRequest);

  void updateEvent(Long eventId, EventDto.UpdateEventRequest updateEventRequest);

  void deleteById(Long eventId);

  void deletesEvent(EventDto.DeleteEventRequest deleteEventRequest);

  Event getEventById(Long eventId);

  void updateExposeById(Long eventId, final CommonDto.UpdateExposeRequest updateExposeRequest) throws IOException;

  EventDto.PageEventResponse getEvents(Pageable pageable, EventDto.SearchRequest searchRequest);

}
