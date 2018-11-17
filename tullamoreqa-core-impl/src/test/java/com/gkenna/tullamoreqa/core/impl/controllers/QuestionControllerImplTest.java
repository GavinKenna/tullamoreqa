package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.exceptions.QuestionInvalidException;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.domain.Entry;
import com.gkenna.tullamoreqa.domain.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionControllerImplTest {

    private static final Logger LOGGER =
            LogManager.getLogger(TagControllerImplTest.class);

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
    public void shouldThrowInvalidQuestion() throws QuestionInvalidException {

        final Long id = 42L;

        when((questionMock).getId()).thenReturn(id);
        when(questionMock.getTitle()).thenReturn(null);

        ResponseEntity responseEntity = questionController.addQuestion(questionMock);

        verify(mockedQuestionService).addQuestion(questionMock);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 201; //CREATED

        assert Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString().equals("http://localhost/question/42");

    }
}
