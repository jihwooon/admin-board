package com.example.admin.demo.faqCategory.repository.impl;

import com.example.admin.demo.common.enums.FaqType;
import com.example.admin.demo.common.error.FaqCategoryNotFoundException;
import com.example.admin.demo.faqCategory.application.FaqCategoryGroupService;
import com.example.admin.demo.faqCategory.application.Impl.FaqCategoryServiceImpl;
import com.example.admin.demo.faqCategory.domain.FaqCategory;
import com.example.admin.demo.faqCategory.domain.FaqCategoryGroup;
import com.example.admin.demo.faqCategory.dto.FaqCategoryDto;
import com.example.admin.demo.faqCategory.repository.FaqCategoryGroupRepository;
import com.example.admin.demo.faqCategory.repository.FaqCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = "spring.profiles.active:integration")
@AutoConfigureMockMvc
class FaqCategoryRepositoryImplTest {

  @Autowired
  private FaqCategoryRepository faqCategoryRepository;

  @Autowired
  private FaqCategoryGroupRepository faqCategoryGroupRepository;

  @MockBean
  private FaqCategoryServiceImpl faqCategoryService;

  @MockBean
  private FaqCategoryGroupService faqCategoryGroupService;

  private Long faqCategoryId;
  private Long faqCategoryGroupId;

  @BeforeEach
  void setUp() {

    faqCategoryId = faqCategoryRepository.save(
        FaqCategory.builder()
            .faqTitle("제목")
            .replayContent("내용")
            .build()).getId();

    faqCategoryGroupId = faqCategoryGroupRepository.save(
        FaqCategoryGroup.builder()
            .faqType(FaqType.STORE_FAQ)
            .title("제목")
            .build()).getId();
  }


  @Test
  void createFaqCategory() {

    FaqCategoryDto.CreateFaqCategoryRequest categoryRequest = new FaqCategoryDto.CreateFaqCategoryRequest();
    categoryRequest.setFaqCategoryTitle("faqTitle");
    categoryRequest.setFaqCategoryContent("replayContent");

    FaqCategoryGroup faqCategoryGroupById = faqCategoryGroupService.getFaqCategoryGroupById(faqCategoryGroupId);

    FaqCategory faqCategory = faqCategoryRepository.save(
        FaqCategory.builder()
            .faqTitle(categoryRequest.getFaqCategoryTitle())
            .faqCategoryGroup(faqCategoryGroupById)
            .replayContent(categoryRequest.getFaqCategoryContent())
            .build());

    assertThat(faqCategory.getFaqTitle()).isEqualTo(categoryRequest.getFaqCategoryTitle());
    assertThat(faqCategory.getReplayContent()).isEqualTo(categoryRequest.getFaqCategoryContent());

  }

  @Test
  void updateExposeIsTrue() {

    FaqCategoryDto.UpdateExposeRequest exposeRequest = new FaqCategoryDto.UpdateExposeRequest();
    exposeRequest.setExpose(true);

    faqCategoryService.updateExposeById(faqCategoryId, exposeRequest);

    FaqCategory faqCategory = faqCategoryRepository.findById(faqCategoryId).get();

    assertThat(faqCategory.isExpose()).isTrue();
  }

  @Test
  void updateExposeIsFalse() {

    FaqCategoryDto.UpdateExposeRequest exposeRequest = new FaqCategoryDto.UpdateExposeRequest();
    exposeRequest.setExpose(false);

    faqCategoryService.updateExposeById(faqCategoryId, exposeRequest);

    FaqCategory faqCategory = faqCategoryRepository.findById(faqCategoryId).get();

    assertThat(faqCategory.isExpose()).isFalse();
  }

  @Test
  void updateExposeIsFalseAndNotExsitedId() {

    FaqCategoryDto.UpdateExposeRequest exposeRequest = new FaqCategoryDto.UpdateExposeRequest();
    exposeRequest.setExpose(false);

    faqCategoryService.updateExposeById(faqCategoryId, exposeRequest);
    FaqCategory faqCategory = faqCategoryRepository.findById(faqCategoryId).get();

    assertThat(faqCategory.isExpose()).isFalse();

  }

  @Test
  void deleteIsFalse() {
    faqCategoryService.deleteById(faqCategoryId);

    FaqCategory faqCategory = faqCategoryRepository.findById(faqCategoryId).get();

    assertThat(faqCategory.isEnable()).isTrue();
  }

  @Test
  void deleteIsTrue() {

    FaqCategory faqCategory = faqCategoryRepository.findById(faqCategoryId).get();

    assertThat(faqCategory.isEnable()).isTrue();

    assertThatThrownBy(() -> faqCategoryService.deleteById(faqCategoryId))
        .isInstanceOf(FaqCategoryNotFoundException.class);
  }

  @Test
  void getFaqCategory() {
    Optional<FaqCategory> faqCategory = faqCategoryRepository.findById(faqCategoryId);

    assertThat(faqCategory).isNotNull();
  }

  @Test
  void getFaqCategoryWithNotExsitedId() {
    assertThatThrownBy(() -> faqCategoryRepository.findById(100L))
        .isInstanceOf(FaqCategoryNotFoundException.class)
        .hasMessage("Not Found Exception");
  }

}
