package com.example.admin.demo.event.controller;

import com.example.admin.demo.event.application.EventService;
import com.example.admin.demo.common.dto.CommonDto;
import com.example.admin.demo.event.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public Long createEvent(@RequestBody @Valid final EventDto.CreateEventRequest createEventRequestRequest) {
    return eventService.createEvent(createEventRequestRequest);
  }

  @GetMapping("/event")
  @ResponseStatus(HttpStatus.OK)
  public CommonDto.PageResponse searchEvent(@RequestParam(value = "page", defaultValue = "0") final int page,
                                            @RequestParam(value = "size", defaultValue = "10") final int size,
                                            @ModelAttribute final EventDto.SearchRequest searchRequest) {
    return eventService.getEvents(PageRequest.of(page, size), searchRequest);
  }

  @GetMapping("/event/{eventId}")
  @ResponseStatus(HttpStatus.OK)
  public EventDto.getEventByIdResponse getEventById(@PathVariable final Long eventId) {
    return EventDto.getEventByIdResponse.of(eventService.getEventById(eventId));
  }

  @PutMapping("/event/{eventId}")
  @ResponseStatus(HttpStatus.OK)
  public void updateEvent(@PathVariable final Long eventId,
                          @RequestBody @Valid final EventDto.UpdateEventRequest updateEventRequest) {
    eventService.updateEvent(eventId, updateEventRequest);
  }

  @PutMapping("/event/{eventId}/expose")
  @ResponseStatus(HttpStatus.OK)
  public void updateExposeById(@PathVariable final Long eventId,
                               @RequestBody @Valid final CommonDto.UpdateExposeRequest updateExposeRequest) {
    eventService.updateExposeById(eventId, updateExposeRequest);
  }

  @DeleteMapping("/event/{eventId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteEvent(@PathVariable final Long eventId) {
    eventService.deleteById(eventId);
  }

  @DeleteMapping("/event")
  @ResponseStatus(HttpStatus.OK)
  public void deletesEvent(@RequestBody @Valid final EventDto.DeleteEventRequest deleteEventRequest) {
    eventService.deletesEvent(deleteEventRequest);
  }

}
