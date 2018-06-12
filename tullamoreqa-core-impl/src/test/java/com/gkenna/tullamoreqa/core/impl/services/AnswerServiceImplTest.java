/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.domain.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnswerServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger(AnswerServiceImplTest.class);

    @InjectMocks
    private final AnswerServiceImpl answerService;
    @Mock
    private final Answer answer;
    private final Long answerId = 0L;
    @Mock
    private AnswerRepository mockedAnswerRepo;

    public AnswerServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        answerService = new AnswerServiceImpl(mockedAnswerRepo);
        answer = new Answer(null, null, null);
    }

    @Test
    public void addAnswer() {
         /*when(mockCustomObject.testMethod()).thenReturn(...);
        mainClass.makeCall();
        verify(mockCustomObject).testMethod();*/
        answerService.addAnswer(answer);
        verify(mockedAnswerRepo).save(answer);
    }

    @Test
    public void deleteAnswerByObject() {
        answerService.deleteAnswer(answer);
        verify(mockedAnswerRepo).delete(answer);
    }

    @Test
    public void deleteAnswerById() throws AnswerNotFoundException {

        when(mockedAnswerRepo.existsById(answerId)).thenReturn(true);
        when(mockedAnswerRepo.getOne(answerId)).thenReturn(answer);

        answerService.deleteAnswer(answerId);

        verify(mockedAnswerRepo).delete(answer);
    }

    @Test
    public void editAnswer() {
    }

    @Test
    public void doesAnswerExist() {
    }

    @Test
    public void doesAnswerExist1() {
    }

    @Test
    public void getAnswer() {
    }

    @Test
    public void getAllAnswers() {
    }

    @Test
    public void findAnswersAnsweredByUser() {
    }
}