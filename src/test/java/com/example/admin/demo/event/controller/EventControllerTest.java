package com.example.admin.demo.event.controller;

import com.example.admin.demo.common.enums.ColorType;
import com.example.admin.demo.event.application.EventService;
import com.example.admin.demo.event.dto.EventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.profiles.active:integration")
@AutoConfigureMockMvc
class EventControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EventService eventService;

  @Autowired
  private ObjectMapper objectMapper;

  Long eventId;

  @BeforeEach
  void setUp() throws Exception {

    EventDto.CreateEventRequest createRequest = new EventDto.CreateEventRequest();

    createRequest.setEventTitle("타이틀");
    createRequest.setEventSubTitle("이벤트 부제목");
    createRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    createRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
    createRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    createRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    createRequest.setColorText(ColorType.BLACK);

    MvcResult createEventMvcResult = mockMvc.perform(post("/api/event")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createRequest)))
        .andReturn();

    eventId = objectMapper.readValue(createEventMvcResult.getResponse().getContentAsString(), Long.class);
    System.out.println("eventId = "+ eventId);

//    EventDto.UpdateEventRequest updateRequest = new EventDto.UpdateEventRequest();
//
//    updateRequest = new EventDto.UpdateEventRequest();
//    updateRequest.setEventTitle("이벤트 수정 타이틀");
//    updateRequest.setEventSubTitle("이벤트 수정 서브 타이틀");
//    updateRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
//    updateRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
//    updateRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
//    updateRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
//    updateRequest.setColorText(ColorType.BLACK);

  }

  @Test
  void create() throws Exception {
    //given
    EventDto.CreateEventRequest createRequest = new EventDto.CreateEventRequest();

    createRequest.setEventTitle("타이틀");
    createRequest.setEventSubTitle("이벤트 부제목");
    createRequest.setEventStart(LocalDateTime.parse("2021-05-11T00:00"));
    createRequest.setEventEnd(LocalDateTime.parse("2021-01-30T00:00"));
    createRequest.setRepImageUrl("https://cdn.pixabay.com/photo/2015/10/08/18/00/puppy-978193_960_720.jpg");
    createRequest.setImageUrl("https://cdn.pixabay.com/photo/2018/09/11/22/19/the-3670813_960_720.jpg");
    createRequest.setColorText(ColorType.BLACK);

    //when
    ResultActions result = mockMvc.perform(post("/api/event")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createRequest))
    );

    //then
    result
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

  @Test
  void list() throws Exception {
    //given
    EventDto.SearchRequest searchRequest = new EventDto.SearchRequest();

    searchRequest.setEventTitle("검색 제목");
    searchRequest.setEventStart(LocalDateTime.parse("2022-05-01T00:00:00"));
    searchRequest.setEventEnd(LocalDateTime.parse("2022-05-31T00:00:00"));

    //when
    ResultActions result = mockMvc.perform(get("/api/event")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(searchRequest))
        .characterEncoding(StandardCharsets.UTF_8.name())
    );

    //then
    result
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  void getById() throws Exception {
    mockMvc.perform(get("/api/event/{eventId}", eventId)
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

//  @Test
//  void update() throws Exception {
//    //given
//
//
//    // when
//    ResultActions result = mockMvc.perform(put("/api/event/{eventId}", eventId)
//        .accept(MediaType.APPLICATION_JSON)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(StandardCharsets.UTF_8.name())
//        .content(objectMapper.writeValueAsString(updateRequest))
//    );
//    //then
//    result
//        .andExpect(status().isOk())
//        .andDo(print())
//        .andReturn();
//  }
//
//  @Test
//  void updateNotFoundId() throws Exception {
//    mockMvc.perform(put("/api/event/100")
//        .accept(MediaType.APPLICATION_JSON)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(StandardCharsets.UTF_8.name())
//        .content(objectMapper.writeValueAsString(updateRequest))
//    )
//        .andExpect(status().isOk())
//        .andDo(print())
//        .andReturn();
//  }

  @Test
  void deleteById() throws Exception {

    mockMvc.perform(delete("/api/event/{eventId}", eventId)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

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
