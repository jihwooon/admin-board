package com.example.admin.demo.controller;

import com.example.admin.demo.application.EventService;
import com.example.admin.demo.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping("/event")
  @ResponseStatus(HttpStatus.CREATED)
  public void CreateEvent(@RequestBody @Valid final EventDto.CreateEventRequest createEventRequestRequest) {
    eventService.createEvent(createEventRequestRequest);
  }

}
