package com.example.admin.demo.controller;

import com.example.admin.demo.application.FaqCategoryGroupService;
import com.example.admin.demo.application.Impl.FaqCategoryGroupServiceImpl;
import com.example.admin.demo.application.error.FaqCategoryGroupIdNotFoundException;
import com.example.admin.demo.domain.FaqCategoryGroup;
import com.example.admin.demo.dto.FaqCategoryGroupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqCategoryGroupController.class)
class FaqCategoryGroupControllerTest {

  private static final Long FAQ_CATEGORY_GROUP_ID = 1L;
  private static final Long FAQ_CATEGORY_GROUP_WRONG_ID = 100L;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FaqCategoryGroupService faqCategoryGroupService;

  @MockBean
  private FaqCategoryGroupServiceImpl faqCategoryGroupServiceImpl;

  @BeforeEach
  void setUp() {
    FaqCategoryGroup faqCategoryGroup = FaqCategoryGroup.builder()
        .title("제목")
        .build();

    given(faqCategoryGroupService.listFaqCategory()).willReturn(List.of());

    given(faqCategoryGroupService.updateFaqCategory(eq(1L),any(FaqCategoryGroupDto.UpdateFaqCategoryRequest.class)))
        .willReturn(FaqCategoryGroupDto.UpdateFaqCategoryGroupResponse.of(faqCategoryGroup));

    given(faqCategoryGroupService.updateFaqCategory(eq(100L), any(FaqCategoryGroupDto.UpdateFaqCategoryRequest.class)))
        .willThrow(new FaqCategoryGroupIdNotFoundException(100L));

  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/category/faqsGroup")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name()))
      .andExpect(status().isOk());
  }

  @Test
  void createNotEmpty() throws Exception {
    mockMvc.perform(post("/category/faqsGroup")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
      .content("{\"title\" : \"제목\"}"))
      .andExpect(status().isCreated());
  }

  @Test
  void createEmpty() throws Exception {
    mockMvc.perform(post("/category/faqsGroup")
      .contentType(MediaType.APPLICATION_JSON)
      .characterEncoding(StandardCharsets.UTF_8.name())
      .content("{}"))
      .andExpect(status().isBadRequest());
  }

  @Test
  void updateNotEmpty() throws Exception {
    mockMvc.perform(patch("/category/faqsGroup/{faqCategoryGroupId}", FAQ_CATEGORY_GROUP_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\": \"수정\"}"))
        .andExpect(status().isOk());
  }

  @Test
  void updateEmpty() throws Exception {
    mockMvc.perform(patch("/category/faqsGroup/{faqCategoryGroupId}", FAQ_CATEGORY_GROUP_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateWrongId() throws Exception {
    mockMvc.perform(patch("/category/faqsGroup/{faqCategoryGroupId}", FAQ_CATEGORY_GROUP_WRONG_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{\"title\": \"수정\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateWrongIdAndEmpty() throws Exception {
    mockMvc.perform(patch("/category/faqsGroup/{faqCategoryGroupId}", FAQ_CATEGORY_GROUP_WRONG_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content("{}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteById() throws Exception {
    mockMvc.perform(delete("/category/faqsGroup/{faqCategoryGroupId}", FAQ_CATEGORY_GROUP_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteByWrongId() throws Exception {
    mockMvc.perform(delete("/category/faqsGroup/{faqCategoryGroupId}", FAQ_CATEGORY_GROUP_WRONG_ID)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name()))
        .andExpect(status().isNotFound());
  }
}
