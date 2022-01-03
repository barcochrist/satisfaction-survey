package com.github.barcochrist.satisfactionsurvey.service.impl;

import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_1;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_1_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_2_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_3;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_3_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_PAGE;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_QUESTION_3;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.ANSWER_QUESTION_LIST;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.EMAIL;
import static com.github.barcochrist.satisfactionsurvey.service.impl.AnswerServiceImplTest.Instance.EMPTY_ANSWER_PAGE;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.MUST_NOT_BE_NULL;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.PAGEABLE;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_1;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_1_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_2;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.QUESTION_2_ID;
import static com.github.barcochrist.satisfactionsurvey.service.impl.CommonUnitTest.VALUE_DOESN_T_MATCH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.github.barcochrist.satisfactionsurvey.config.exception.NotFoundException;
import com.github.barcochrist.satisfactionsurvey.entity.AnswerJpa;
import com.github.barcochrist.satisfactionsurvey.entity.AnswerQuestionJpa;
import com.github.barcochrist.satisfactionsurvey.model.Answer;
import com.github.barcochrist.satisfactionsurvey.model.AnswerQuestion;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerQuestionRepository;
import com.github.barcochrist.satisfactionsurvey.repository.AnswerRepository;
import com.github.barcochrist.satisfactionsurvey.repository.QuestionRepository;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerDraftResource;
import com.github.barcochrist.satisfactionsurvey.resource.AnswerQuestionDraftResource;
import com.github.barcochrist.satisfactionsurvey.service.AnswerService;
import java.util.List;
import java.util.Optional;
import javax.persistence.NonUniqueResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

class AnswerServiceImplTest {

  @Mock
  private AnswerRepository answerRepository;
  @Mock
  private AnswerQuestionRepository answerQuestionRepository;
  @Mock
  private QuestionRepository questionRepository;

  private AnswerService service;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    service = new AnswerServiceImpl(answerRepository, answerQuestionRepository, questionRepository);
  }

  @Test
  void create() {
    when(answerRepository.findByEmail(any())).thenReturn(Optional.empty());
    when(questionRepository.findRequired()).thenReturn(List.of());
    when(questionRepository.findById(anyString())).thenReturn(Optional.of(QUESTION_2));
    when(answerQuestionRepository.create(any())).thenReturn(ANSWER_QUESTION_3);
    when(answerRepository.create(any())).thenReturn(ANSWER_3);

    var aqDraft = AnswerQuestionDraftResource.builder()
        .questionId(QUESTION_2_ID)
        .response("Respuesta Y")
        .build();

    var response = service.create(
        AnswerDraftResource.builder()
            .email(EMAIL)
            .customerName("Usuario 3")
            .answers(List.of(aqDraft))
            .build()
    );
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(ANSWER_3_ID, response.getId(), VALUE_DOESN_T_MATCH);
    assertEquals(EMAIL, response.getEmail(), VALUE_DOESN_T_MATCH);
    assertEquals("Usuario 3", response.getCustomerName(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void createUserAlreadyDoneSurvey() {
    when(answerRepository.findByEmail(any())).thenReturn(Optional.of(ANSWER_1));

    var response = assertThrows(NonUniqueResultException.class, () ->
        service.create(
            AnswerDraftResource.builder()
                .email("usuario4@mail.com")
                .customerName("Usuario 4")
                .answers(List.of())
                .build()
        )
    );

    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals("The customer with the email \"usuario4@mail.com\" has already done the survey",
        response.getMessage(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void createEmptyRequiredQuestion() {
    when(answerRepository.findByEmail(any())).thenReturn(Optional.empty());
    when(questionRepository.findRequired()).thenReturn(List.of(QUESTION_2));

    var aqDraft = AnswerQuestionDraftResource.builder()
        .questionId(QUESTION_1_ID)
        .response("7059aaf7-0118-47ad-9bbd-aa21559c541b")
        .build();

    var response = assertThrows(IllegalArgumentException.class, () ->
        service.create(
            AnswerDraftResource.builder()
                .email(EMAIL)
                .customerName("Usuario 5")
                .answers(List.of(aqDraft))
                .build()
        )
    );

    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals("Please answer the following mandatory question: \"Pregunta Y\"",
        response.getMessage(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void createQuestionNotFound() {
    when(answerRepository.findByEmail(any())).thenReturn(Optional.empty());
    when(questionRepository.findRequired()).thenReturn(List.of());
    when(questionRepository.findById(anyString())).thenReturn(Optional.empty());

    var aqDraft = AnswerQuestionDraftResource.builder()
        .questionId("7059aaf7-0118-47ad-9bbd")
        .response("Respuesta XYZ")
        .build();

    var response = assertThrows(NotFoundException.class, () ->
        service.create(
            AnswerDraftResource.builder()
                .email(EMAIL)
                .customerName("Usuario 4")
                .answers(List.of(aqDraft))
                .build()
        )
    );

    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals("Question with identifier 7059aaf7-0118-47ad-9bbd, not found",
        response.getMessage(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void createOpenQuestionError() {
    when(answerRepository.findByEmail(any())).thenReturn(Optional.empty());
    when(questionRepository.findRequired()).thenReturn(List.of());
    when(questionRepository.findById(anyString())).thenReturn(Optional.of(QUESTION_2));

    var aqDraft = AnswerQuestionDraftResource.builder()
        .questionId(QUESTION_2_ID)
        .build();

    var response = assertThrows(IllegalArgumentException.class, () ->
        service.create(
            AnswerDraftResource.builder()
                .email(EMAIL)
                .customerName("Usuario 6")
                .answers(List.of(aqDraft))
                .build()
        )
    );

    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals("Please give an answer to the question: \"Pregunta Y\"",
        response.getMessage(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void createMultipleChoiceQuestionError() {
    when(answerRepository.findByEmail(any())).thenReturn(Optional.empty());
    when(questionRepository.findRequired()).thenReturn(List.of());
    when(questionRepository.findById(anyString())).thenReturn(Optional.of(QUESTION_1));

    var aqDraft = AnswerQuestionDraftResource.builder()
        .questionId(QUESTION_1_ID)
        .build();

    var response = assertThrows(IllegalArgumentException.class, () ->
        service.create(
            AnswerDraftResource.builder()
                .email(EMAIL)
                .customerName("Usuario 6")
                .answers(List.of(aqDraft))
                .build()
        )
    );

    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals("Please select at least one option for question \"Pregunta X\"",
        response.getMessage(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findAll() {
    when(service.findAll(any())).thenReturn(ANSWER_PAGE);
    var response = service.findAll(PAGEABLE);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertNotNull(response.getContent(), MUST_NOT_BE_NULL);
    assertEquals(2, response.getContent().size(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findAllEmpty() {
    when(service.findAll(any())).thenReturn(EMPTY_ANSWER_PAGE);
    var response = service.findAll(PAGEABLE);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(0, response.getTotalElements(), VALUE_DOESN_T_MATCH);
    assertNotNull(response.getContent(), MUST_NOT_BE_NULL);
    assertTrue(response.getContent().isEmpty(), VALUE_DOESN_T_MATCH);
  }

  @Test
  void findAnswerQuestionsByAnswerId() {
    when(service.findAnswerQuestionsByAnswerId(any())).thenReturn(ANSWER_QUESTION_LIST);
    var response = service.findAnswerQuestionsByAnswerId(ANSWER_1_ID);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertEquals(2, response.size(), VALUE_DOESN_T_MATCH);
    assertNotNull(response.get(0), MUST_NOT_BE_NULL);
  }

  @Test
  void findAnswerQuestionsByAnswerIdEmpty() {
    when(service.findAnswerQuestionsByAnswerId(any())).thenReturn(List.of());
    var response = service.findAnswerQuestionsByAnswerId(ANSWER_2_ID);
    assertNotNull(response, MUST_NOT_BE_NULL);
    assertTrue(response.isEmpty(), VALUE_DOESN_T_MATCH);
  }

  interface Instance {

    String EMAIL = "email@mail.com";

    String ANSWER_1_ID = "d6691d48-2daf-45fc-9299-f73c4f532d82";
    Answer ANSWER_1 = AnswerJpa.builder()
        .id(ANSWER_1_ID)
        .email("jmilton@gmail.com")
        .customerName("Jhon Milton")
        .build();

    String ANSWER_2_ID = "834d4cfe-3c3a-45e1-bebd-96aa0cddceb2";
    Answer ANSWER_2 = AnswerJpa.builder()
        .id(ANSWER_2_ID)
        .email("email@gmail.com")
        .customerName("Usuario 2")
        .build();

    String ANSWER_3_ID = "9837f194-0e27-44b4-98fd-409157ae01ec";
    Answer ANSWER_3 = AnswerJpa.builder()
        .id(ANSWER_3_ID)
        .email(EMAIL)
        .customerName("Usuario 3")
        .build();

    Page<Answer> ANSWER_PAGE = new PageImpl<>(List.of(ANSWER_1, ANSWER_2));

    AnswerQuestion ANSWER_QUESTION_1 = AnswerQuestionJpa.builder()
        .id("f68f2ff3-4e0c-4b07-9311-09ace2def8a7")
        .questionId("7059aaf7-0118-47ad-9bbd-aa21559c540a")
        .answerId(ANSWER_1_ID)
        .response("Comentario de Usuario")
        .build();

    AnswerQuestion ANSWER_QUESTION_2 = AnswerQuestionJpa.builder()
        .id("2dbf57cb-26b5-482c-a74a-a4751afebb32")
        .questionId("7059aaf7-0118-47ad-9bbd-aa21559c540b")
        .answerId(ANSWER_1_ID)
        .response("7059aaf7-0118-47ad-9bbd-aa21559c541c")
        .build();

    AnswerQuestion ANSWER_QUESTION_3 = AnswerQuestionJpa.builder()
        .id("8f7a04f6-74d8-4be0-a244-30a5d82c7862")
        .questionId("7059aaf7-0118-47ad-9bbd-aa21559c540a")
        .answerId(ANSWER_3_ID)
        .response("COMENTARIO 3")
        .build();

    List<AnswerQuestion> ANSWER_QUESTION_LIST = List.of(ANSWER_QUESTION_1, ANSWER_QUESTION_2);
    Page<Answer> EMPTY_ANSWER_PAGE = Page.empty();
  }
}