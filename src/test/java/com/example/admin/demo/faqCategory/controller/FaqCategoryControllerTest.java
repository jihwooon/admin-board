package com.example.admin.demo.faqCategory.controller;

import com.example.admin.demo.common.enums.FaqType;
import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import com.example.admin.demo.faqCategory.dto.FaqCategoryGroupDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.profiles.active:integration")
@AutoConfigureMockMvc
class FaqCategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private Long faqCategoryGroupId;

  private Long faqId;

  @BeforeEach
  void setUp() throws Exception {
    FaqCategoryGroupDto.CreateFaqCategoryGroupRequest createFaqCategoryGroupRequest = new FaqCategoryGroupDto.CreateFaqCategoryGroupRequest();
    createFaqCategoryGroupRequest.setFaqCategoryGroupTitle("FaqCategoryGroup 제목");
    createFaqCategoryGroupRequest.setFaqType(FaqType.STORE_FAQ);

    MvcResult faqCategoryGroupResult = mockMvc.perform(post("/api/faqsGroup")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createFaqCategoryGroupRequest)))
        .andReturn();

    faqCategoryGroupId = objectMapper.readValue(faqCategoryGroupResult.getResponse().getContentAsString(), Long.class);

    FaqCategoryDto.CreateFaqCategoryRequest createFaqCategoryRequest = new FaqCategoryDto.CreateFaqCategoryRequest();
    createFaqCategoryRequest.setFaqCategoryTitle("FaqCategory제목");
    createFaqCategoryRequest.setFaqCategoryContent("FaqCategory내용");

    MvcResult faqCategoryResult = mockMvc.perform(post("/api/faqsGroup/{faqCategoryGroupId}/faqs", faqCategoryGroupId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createFaqCategoryRequest)))
        .andReturn();

    faqId = objectMapper.readValue(faqCategoryResult.getResponse().getContentAsString(), Long.class);
  }

  @Test
  void createFaqCategory() throws Exception {
    FaqCategoryDto.CreateFaqCategoryRequest createFaqCategoryRequest = new FaqCategoryDto.CreateFaqCategoryRequest();
    createFaqCategoryRequest.setFaqCategoryTitle("FaqCategory제목");
    createFaqCategoryRequest.setFaqCategoryContent("FaqCategory내용");

    mockMvc.perform(post("/api/faqsGroup/{faqCategoryGroupId}/faqs", faqCategoryGroupId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createFaqCategoryRequest))
    )
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

  @Test
  void getFaqCategories() throws Exception {
    FaqCategoryDto.SearchRequest searchRequest = new FaqCategoryDto.SearchRequest();
    searchRequest.setFaqCategoryGroupId(faqCategoryGroupId);

    mockMvc.perform(get("/api/faqs")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(searchRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  void getFaqCategory() throws Exception {
    mockMvc.perform(get("/api/faqsGroup/{faqCategoryGroupId}/faqs/{faqId}", faqCategoryGroupId, faqId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  void updateExposeById() throws Exception {
    FaqCategoryDto.UpdateExposeRequest updateExposeRequest = new FaqCategoryDto.UpdateExposeRequest();
    updateExposeRequest.setExpose(true);

    mockMvc.perform(put("/api/faqs/{faqId}/expose", faqId)
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
  void deleteFaqCategoryById() throws Exception {
    mockMvc.perform(delete("/api/faqs/{faqId}", faqId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  void deleteFaqCategories() throws Exception {
    FaqCategoryDto.DeleteFaqCategoryRequest deleteFaqCategoryRequest = new FaqCategoryDto.DeleteFaqCategoryRequest();
    List<Long> faqCategories = new ArrayList<>();
    faqCategories.add(faqId);

    deleteFaqCategoryRequest.setFaqCategories(faqCategories);

    mockMvc.perform(delete("/api/faqs")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(objectMapper.writeValueAsString(deleteFaqCategoryRequest))
    )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }
}
