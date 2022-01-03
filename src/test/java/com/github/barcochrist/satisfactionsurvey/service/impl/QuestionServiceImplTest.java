package com.github.barcochrist.satisfactionsurvey.service.impl;

import static com.github.barcochrist.satisfactionsurvey.service.impl.QuestionServiceImplTest.Instance.EMPTY_QUESTION_PAGE;
import static com.github.barcochrist.satisfactionsurvey.service.impl.QuestionServiceImplTest.Instance.QUESTION_OPTION_1_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.QuestionServiceImplTest.Instance.QUESTION_OPTION_2_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.QuestionServiceImplTest.Instance.QUESTION_OPTION_LIST;
import static com.github.barcochrist.satisfactionsurvey.service.impl.QuestionServiceImplTest.Instance.QUESTION_PAGE;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.MUST_BE_NULL;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.MUST_NOT_BE_NULL;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.PAGEABLE;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_1;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_1_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_2;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.VALUE_DOESN_T_MATCH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.github.barcochrist.satisfactionsurvey.entity.QuestionOptionJpa;
import com.github.barcochrist.satisfactionsurvey.model.Question;
import com.github.barcochrist.satisfactionsurvey.model.QuestionOption;
import com.github.barcochrist.satisfactionsurvey.model.enums.QuestionType;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionOptionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.service.QuestionService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

class QuestionServiceImplTest {

  @Mock
  private QuestionRepository questionRepository;
  @Mock
  private QuestionOptionRepository questionOptionRepository;

  private QuestionService service;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    service = new QuestionServiceImpl(questionRepository, questionOptionRepository);
  }

  @Test
  void findById() {
    when(questionRepository.findById(anyString())).thenReturn(Optional.of(QUESTION_1));
    var response = service.findById(QUESTION_1_ID);
    assertTrue(response.isPresent(), MUST_NOT_BE_NULL);
    assertEquals(QUESTION_1_ID, response.get().getId(), VALUE_DOESN_T_MATCH);
    assertEquals("Pregunta X", response.get().getTitle(), VALUE_DOESN_T_MATCH);
    assertEquals(QuestionType.MULTIPLE_CHOICE, response.get().getType(), VALUE_DOESN_T_MATCH);
    assertTrue(response.get().getIsRequired(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findByIdNotFound() {
    when(questionRepository.findById(anyString())).thenReturn(Optional.empty());
    var response = service.findById(QUESTION_1_ID);
    assertTrue(response.isEmpty(), MUST_BE_NULL);
  }

  @Test
  void findOptionsByQuestionId() {
    when(service.findOptionsByQuestionId(anyString())).thenReturn(QUESTION_OPTION_LIST);
    var response = service.findOptionsByQuestionId(QUESTION_1_ID);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(2, response.size(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findOptionsByQuestionIdEmpty() {
    when(service.findOptionsByQuestionId(anyString())).thenReturn(List.of());
    var response = service.findOptionsByQuestionId("812j2i1");
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertTrue(response.isEmpty(), VALUE_DOESN_T_MATCH);
    assertEquals(0, response.size(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findSeveralOptions() {
    when(service.findSeveralOptions(any())).thenReturn(QUESTION_OPTION_LIST);
    var response = service.findSeveralOptions(List.of(QUESTION_OPTION_1_ID, QUESTION_OPTION_2_ID));
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(2, response.size(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findSeveralOptionsEmpty() {
    when(service.findSeveralOptions(any())).thenReturn(List.of());
    var response = service.findSeveralOptions(List.of(QUESTION_OPTION_1_ID, QUESTION_OPTION_2_ID));
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertTrue(response.isEmpty(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findAll() {
    when(service.findAll(any())).thenReturn(QUESTION_PAGE);
    var response = service.findAll(PAGEABLE);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(2, response.getTotalElements(), VALUE_DOESN_T_MATCH);
    assertNotNull(response.getContent(), MUST_NOT_BE_NULL);
    assertEquals(2, response.getContent().size(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findAllEmpty() {
    when(service.findAll(any())).thenReturn(EMPTY_QUESTION_PAGE);
    var response = service.findAll(PAGEABLE);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(0, response.getTotalElements(), VALUE_DOESN_T_MATCH);
    assertNotNull(response.getContent(), MUST_NOT_BE_NULL);
    assertTrue(response.getContent().isEmpty(), VALUE_DOESN_T_MATCH);
  }

  interface Instance {

    Page<Question> QUESTION_PAGE = new PageImpl<>(List.of(QUESTION_1, QUESTION_2));

    String QUESTION_OPTION_1_ID = "7059aaf7-0118-47ad-9bbd-aa21559c549a";
    QuestionOption QUESTION_OPTION_1 = QuestionOptionJpa.builder()
        .id(QUESTION_OPTION_1_ID)
        .questionId(QUESTION_1_ID)
        .name("Opcion 1")
        .build();

    String QUESTION_OPTION_2_ID = "7059aaf7-0118-47ad-9bbd-aa21559c549a";
    QuestionOption QUESTION_OPTION_2 = QuestionOptionJpa.builder()
        .id(QUESTION_OPTION_2_ID)
        .questionId(QUESTION_1_ID)
        .name("Opcion 2")
        .build();

    List<QuestionOption> QUESTION_OPTION_LIST = List.of(QUESTION_OPTION_1, QUESTION_OPTION_2);
    Page<Question> EMPTY_QUESTION_PAGE = Page.empty();
  }
}