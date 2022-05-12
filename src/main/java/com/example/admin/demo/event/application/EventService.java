package com.example.admin.demo.event.application;

import com.example.admin.demo.event.domain.Event;
import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.event.dto.EventDto;
import org.springframework.data.domain.Pageable;

public interface EventService {

  Long createEvent(EventDto.CreateEventRequest createEventRequestRequest);

  void updateEvent(Long eventId,
                   EventDto.UpdateEventRequest updateEventRequest);

  void deleteById(Long eventId);

  void deletesEvent(EventDto.DeleteEventRequest deleteEventRequest);

  Event getEventById(Long eventId);

  void updateExposeById(Long eventId,
                        CommonDto.UpdateExposeRequest updateExposeRequest);

  CommonDto.PageResponse getEvents(Pageable pageable,
                                   EventDto.SearchRequest searchRequest);

}
