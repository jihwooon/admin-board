package com.example.admin.demo.notice.controller;

import com.example.admin.demo.notice.application.NoticeService;
import com.example.admin.demo.notice.domain.Notice;
import com.example.admin.demo.notice.dto.NoticeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoticeController.class)
@MockBean(JpaMetamodelMappingContext.class)
class NoticeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NoticeService noticeService;

  @Autowired
  private ObjectMapper objectMapper;


  @BeforeEach
  void setUp() {
   Notice notice = Notice.builder()
        .noticeTitle("notice제목")
        .noticeContents("notice내용")
        .build();
  }

  @Test
  void createNotice() throws Exception {
    NoticeDto.CreateNoticeRequest createRequest = new NoticeDto.CreateNoticeRequest();
    createRequest.setNoticeTitle("Notice제목");
    createRequest.setNoticeContents("Notcie내용");

    mockMvc.perform(post("/api/notice")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(createRequest))
    )
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

}
