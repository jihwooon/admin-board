package com.example.admin.demo.application;

import com.example.admin.demo.dto.EventDto;

public interface EventService {

  void createEvent(EventDto.CreateEventRequest createEventRequestRequest);
}
