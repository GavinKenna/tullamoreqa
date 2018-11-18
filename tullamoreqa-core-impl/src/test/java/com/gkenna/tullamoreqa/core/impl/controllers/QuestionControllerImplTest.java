package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.exceptions.QuestionInvalidException;
import com.gkenna.tullamoreqa.core.api.exceptions.QuestionNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashSet;
import java.util.Objects;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionControllerImplTest {

    private static final Logger LOGGER =
            LogManager.getLogger(QuestionControllerImplTest.class);

    @InjectMocks
    private final QuestionControllerImpl questionController;

    @Mock
    private QuestionService mockedQuestionService;

    @Mock
    private RequestAttributes attrs;

    @Mock
    private Question questionMock;

    public QuestionControllerImplTest() {
        MockitoAnnotations.initMocks(this);
        questionController = new QuestionControllerImpl(mockedQuestionService);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/question");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void shouldAddQuestionSuccessfully() throws QuestionInvalidException {

        final Long id = 42L;
        questionMock.setTitle("How do humans convey emotion?");
        questionMock.setBody("Please advise.");

        when((questionMock).getId()).thenReturn(id);

        ResponseEntity responseEntity = questionController.addQuestion(questionMock);

        verify(mockedQuestionService).addQuestion(questionMock);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 201; //CREATED

        assert Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString().equals("http://localhost/question/42");

    }

    @Test
    public void shouldThrowInvalidQuestionDueToTitle() throws QuestionInvalidException {

        final Long id = 42L;

        when((questionMock).getId()).thenReturn(id);
        when(questionMock.getTitle()).thenReturn(null);
        when(questionMock.getBody()).thenReturn("I am a valid body!");

        doThrow(QuestionInvalidException.class).when(mockedQuestionService).addQuestion(questionMock);

        ResponseEntity responseEntity = questionController.addQuestion(questionMock);

        verify(mockedQuestionService).addQuestion(questionMock);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 400; //BAD_REQUEST
    }

    @Test
    public void shouldThrowInvalidQuestionDueToBody() throws QuestionInvalidException {

        final Long id = 42L;

        when((questionMock).getId()).thenReturn(id);
        when(questionMock.getTitle()).thenReturn("I have a valid title!");
        when(questionMock.getBody()).thenReturn(null);

        doThrow(QuestionInvalidException.class).when(mockedQuestionService).addQuestion(questionMock);

        ResponseEntity responseEntity = questionController.addQuestion(questionMock);

        verify(mockedQuestionService).addQuestion(questionMock);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 400; //BAD_REQUEST
    }

    @Test
    public void shouldGetValidQuestionSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setBody("I am to be returned.");
        question.setTitle("I am the title.");
        question.setVotes(new HashSet<>());
        question.setTags(new HashSet<>());
        question.setModifiedBy(new User("Gavin"));

        when(mockedQuestionService.getQuestion(42L)).thenReturn(question);

        ResponseEntity responseEntity = questionController.getQuestion(42L);

        verify(mockedQuestionService).getQuestion(42L);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 200; // OK

        assert responseEntity.getBody().equals(question);
    }

    @Test
    public void shouldGetNoQuestion() throws QuestionNotFoundException {

        doThrow(QuestionNotFoundException.class).when(mockedQuestionService).getQuestion(42L);

        ResponseEntity responseEntity = questionController.getQuestion(42L);

        verify(mockedQuestionService).getQuestion(42L);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }

    @Test
    public void shouldDeleteQuestionSuccessfully() throws QuestionNotFoundException {

        ResponseEntity responseEntity = questionController.deleteQuestion(42L);

        verify(mockedQuestionService).deleteQuestion(42L);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 204; // NO_CONTENT

    }

    @Test
    public void shouldGetNoQuestionWhenDeleting() throws QuestionNotFoundException {

        doThrow(QuestionNotFoundException.class).when(mockedQuestionService).deleteQuestion(42L);

        ResponseEntity responseEntity = questionController.deleteQuestion(42L);

        verify(mockedQuestionService).deleteQuestion(42L);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }

    @Test
    public void shouldUpdateQuestionSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setBody("I am to be returned.");
        question.setTitle("I am the title.");
        question.setVotes(new HashSet<>());
        question.setTags(new HashSet<>());
        question.setModifiedBy(new User("Gavin"));

        when(mockedQuestionService.updateQuestion(42L,question)).thenReturn(question);

        ResponseEntity responseEntity = questionController.updateQuestion(42L, question);

        verify(mockedQuestionService).updateQuestion(42L, question);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 200; // OK

        assert responseEntity.getBody().equals(question);
    }

    @Test
    public void shouldThrowExceptionWhenUpdating() throws QuestionNotFoundException {
        final Question question = new Question();

        doThrow(QuestionNotFoundException.class).when(mockedQuestionService).updateQuestion(42L, question);

        ResponseEntity responseEntity = questionController.updateQuestion(42L, question);

        verify(mockedQuestionService).updateQuestion(42L, question);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }

    @Test
    public void shouldPatchQuestionSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setBody("I am to be returned.");
        question.setModifiedBy(new User("Gavin"));

        when(mockedQuestionService.patchQuestion(42L,question)).thenReturn(question);

        ResponseEntity responseEntity = questionController.patchQuestion(42L, question);

        verify(mockedQuestionService).patchQuestion(42L, question);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 200; // OK

        assert responseEntity.getBody().equals(question);
    }

    @Test
    public void shouldThrowExceptionWhenPatching() throws QuestionNotFoundException {
        final Question question = new Question();

        doThrow(QuestionNotFoundException.class).when(mockedQuestionService).patchQuestion(42L, question);

        ResponseEntity responseEntity = questionController.patchQuestion(42L, question);

        verify(mockedQuestionService).patchQuestion(42L, question);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }
}
