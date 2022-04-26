package com.example.admin.demo.controller;

import com.example.admin.demo.application.Impl.FaqCategoryGroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FaqCategoryGroupControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FaqCategoryGroupServiceImpl faqCategoryGroupServiceimpl;

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/faqs")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name()))
      .andExpect(status().isOk());
  }
}
