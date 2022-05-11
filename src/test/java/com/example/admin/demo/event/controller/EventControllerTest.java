package com.example.admin.demo.event.controller;

import com.example.admin.demo.common.error.EventNotFoundException;
import com.example.admin.demo.event.application.EventService;
import com.example.admin.demo.event.domain.Event;
import com.example.admin.demo.event.dto.EventDto;
import com.example.admin.demo.event.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.admin.demo.common.enums.ColorType.BLACK;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EventControllerTest extends AbstractIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EventService eventService;

  @Autowired
  private ObjectMapper objectMapper;

  private EventDto.CreateEventRequest createRequest;
  private EventDto.UpdateEventRequest updateRequest;

  @MockBean
  private EventRepository eventRepository;

  @BeforeEach
  void setUp() {
    eventRepository.deleteAll();
    Event event = Event.builder()
        .eventTitle("타이틀")
        .eventSubTitle("이벤트 부제목")
        .imageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg")
        .repImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg")
        .eventStart(LocalDateTime.parse("2021-01-30T00:00"))
        .eventEnd(LocalDateTime.parse("2021-01-30T00:00"))
        .colorType(BLACK)
        .build();

    createRequest = new EventDto.CreateEventRequest();
    createRequest.setEventTitle("타이틀");
    createRequest.setEventSubTitle("이벤트 부제목");
    createRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    createRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
    createRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    createRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    createRequest.setColorText(BLACK);

    updateRequest = new EventDto.UpdateEventRequest();
    updateRequest.setEventTitle("타이틀");
    updateRequest.setEventSubTitle("이벤트 부제목");
    updateRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    updateRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
    updateRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    updateRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    updateRequest.setColorText(BLACK);

    given(eventService.getEventById(1L)).willReturn(event);
    given(eventService.getEventById(100L)).willThrow(new EventNotFoundException("Not Found id"));

  }

  @Test
  void create() throws Exception {

    mockMvc.perform(post("/api/event")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createRequest))
    )
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/api/event/search")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void getById() throws Exception {
    mockMvc.perform(get("/api/event/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void getByNotFoundId() throws Exception {
    mockMvc.perform(get("/api/event/100")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  void update() throws Exception {
    mockMvc.perform(put("/api/event/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  void updateNotFoundId() throws Exception {
    mockMvc.perform(put("/api/event/100")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  void deleteById() throws Exception {
    long eventId = 1L;

    mockMvc.perform(delete("/api/event/{eventId}", eventId)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

    verify(eventService).deleteById(eventId);
  }

  @Test
  void deletesEvent() throws Exception {
    List<Long> events = new ArrayList<>();
    events.add(1L);
    events.add(2L);

    EventDto.DeleteEventRequest deletesRequest = new EventDto.DeleteEventRequest();
    deletesRequest.setEventIds(events);

    mockMvc.perform(delete("/api/event")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(deletesRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

  }
}
