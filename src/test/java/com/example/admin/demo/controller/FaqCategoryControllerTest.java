package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryService;
import com.example.admin.demo.application.Impl.FaqCategoryServiceImpl;
import com.example.admin.demo.domain.FaqCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqCategoryController.class)
class FaqCategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FaqCategoryService faqCategoryService;

  @MockBean
  private FaqCategoryServiceImpl faqCategoryServiceImpl;

  @Autowired
  private FaqCategoryController faqCategoryController;

  @BeforeEach
  void setUp() {
    FaqCategory.builder()
        .title("제목")
        .content("내용")
        .build();

    given(faqCategoryService.listFaqCategory()).willReturn(List.of());
  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/faqs")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isOk());
  }
}
