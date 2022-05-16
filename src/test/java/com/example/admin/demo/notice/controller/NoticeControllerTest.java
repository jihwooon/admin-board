package com.example.admin.demo.notice.controller;

import com.example.admin.demo.notice.dto.NoticeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.profiles.active:integration")
@AutoConfigureMockMvc
class NoticeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private Long noticeId;

  @BeforeEach
  void setUp() throws Exception {

    NoticeDto.CreateNoticeRequest createRequest = new NoticeDto.CreateNoticeRequest();
    createRequest.setNoticeTitle("Notice제목");
    createRequest.setNoticeContents("Notice내용");

    MvcResult createResult = mockMvc.perform(post("/api/notice")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(createRequest))
    )
        .andDo(print())
        .andReturn();

    noticeId = objectMapper.readValue(createResult.getResponse().getContentAsString(), Long.class);

  }

  @Test
  @DisplayName("공지사항 등록")
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

  @Test
  @DisplayName("공지사항 단건 조회")
  void getNoticeById() throws Exception {
    mockMvc.perform(get("/api/notice/{noticeId}", noticeId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("Notice제목").doesNotExist())
        .andExpect(jsonPath("Notcie내용").doesNotExist())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 단건 조회 예외")
  void getNoticeByNotFoundId() throws Exception {
    mockMvc.perform(get("/api/notice/100")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 검색 조회 ")
  void getNotices() throws Exception {
    NoticeDto.SearchRequest searchRequest = new NoticeDto.SearchRequest();
    searchRequest.setNoticeTitle("제목");

    mockMvc.perform(get("/api/notice")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(searchRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 변경")
  void updateNotice() throws Exception {
    NoticeDto.UpdateNoticeRequest updateRequest = new NoticeDto.UpdateNoticeRequest();
    updateRequest.setNoticeTitle("수정제목");
    updateRequest.setNoticeContents("수정내용");

    mockMvc.perform(put("/api/notice/{noticeId}", noticeId)
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
  @DisplayName("공지사항 변경 실패")
  void updateNoticeNotExistedById() throws Exception {
    NoticeDto.UpdateNoticeRequest updateRequest = new NoticeDto.UpdateNoticeRequest();
    updateRequest.setNoticeTitle("수정제목");
    updateRequest.setNoticeContents("수정내용");

    mockMvc.perform(put("/api/notice/100")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateRequest))
    )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 노출 True인 경우")
  void updateExposeById() throws Exception {
    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    mockMvc.perform(put("/api/notice/{noticeId}/expose", noticeId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateExposeRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 노출 Id값이 다른 경우")
  void updateExposeNotExistedById() throws Exception {
    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    mockMvc.perform(put("/api/notice/100/expose")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateExposeRequest))
    )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 노출 False인 경우")
  void updateExposeByCaseFalse() throws Exception {
    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    mockMvc.perform(put("/api/notice/{noticeId}/expose", noticeId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateExposeRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 노출 False이면서 Id값이 다른 경우")
  void updateExposeByCaseFalseAndNotExistedId() throws Exception {
    NoticeDto.UpdateExposeRequest updateExposeRequest = new NoticeDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(false);

    mockMvc.perform(put("/api/notice/100/expose")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(updateExposeRequest))
    )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 단건 삭제")
  void deleteNoticeById() throws Exception {
    mockMvc.perform(delete("/api/notice/{noticeId}", noticeId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 단건 삭제 Id값이 다른 경우")
  void deleteNoticeByNotExistedId() throws Exception {
    mockMvc.perform(delete("/api/notice/100")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  @DisplayName("공지사항 전체 삭제")
  void deleteNotices() throws Exception {
    NoticeDto.DeleteTotalNoticeRequest deleteNotices = new NoticeDto.DeleteTotalNoticeRequest();
    List<Long> notices = new ArrayList<>();
    notices.add(noticeId);

    deleteNotices.setNoticesId(notices);

    mockMvc.perform(delete("/api/notice")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(deleteNotices))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

}
