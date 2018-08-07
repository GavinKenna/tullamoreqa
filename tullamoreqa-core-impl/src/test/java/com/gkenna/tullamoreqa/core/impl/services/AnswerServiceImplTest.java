/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        answerService.addAnswer(answer);
        verify(mockedAnswerRepo).save(answer);
        //verify(mockedAnswerRepo).saveAndFlush(answer);
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

    @Test(expected = AnswerNotFoundException.class)
    public void deleteAnswerByInvalidId() throws AnswerNotFoundException {
        when(mockedAnswerRepo.existsById(answerId)).thenReturn(false);
        answerService.deleteAnswer(answerId);
    }

    @Test
    public void updateAnswer() throws AnswerNotFoundException {
        Question q = new Question();
        q.setTitle("Am I Updated?");
        q.setBody("Let's find out");

        User u = new User("Numberwang");
        String body = "Updated Body.";

        Answer updateAnswerToThis = new Answer(q, u, body);

        when(mockedAnswerRepo.existsById(answerId)).thenReturn(true);
        when(mockedAnswerRepo.getOne(answerId)).thenReturn(answer);
        answerService.updateAnswer(answerId, updateAnswerToThis);

        verify(mockedAnswerRepo).save(answer);

        assert answer.getQuestion().equals(q);
        assert answer.getBody().equals(body);
        assert answer.getUser().equals(u);
    }

    @Test(expected = AnswerNotFoundException.class)
    public void updateAnswerInvalidIdTest() throws AnswerNotFoundException {
        when(mockedAnswerRepo.existsById(answerId)).thenReturn(false);
        answerService.updateAnswer(answerId, answer);
    }

    @Test
    public void doesAnswerExistByAnswerObject() {
        when(mockedAnswerRepo.existsById(answer.getId())).thenReturn(false);
        boolean doesExist = answerService.doesAnswerExist(answer);
        verify(mockedAnswerRepo).existsById(answer.getId());

        assert !doesExist;
    }

    @Test
    public void doesAnswerExistByAnswerId() {
        when(mockedAnswerRepo.existsById(answerId)).thenReturn(true);
        boolean doesExist = answerService.doesAnswerExist(answerId);
        verify(mockedAnswerRepo).existsById(answerId);

        assert doesExist;
    }

    @Test
    public void getAnswer() throws AnswerNotFoundException {
        Optional<Answer> optionalAnswer = Optional.ofNullable(answer);
        when(mockedAnswerRepo.findById(answerId)).thenReturn(optionalAnswer);

        Answer a = answerService.getAnswer(answerId);
        assert answer.equals(a);
    }

    @Test(expected = AnswerNotFoundException.class)
    public void getAnswerInvalidId() throws AnswerNotFoundException {
        Optional<Answer> optionalAnswer = Optional.empty();
        when(mockedAnswerRepo.findById(answerId)).thenReturn(optionalAnswer);

        Answer a = answerService.getAnswer(answerId);
    }

    @Test
    public void getAllAnswers() {
        List<Answer> listOfAnswers = new ArrayList<>();
        listOfAnswers.add(answer);
        when(mockedAnswerRepo.findAll()).thenReturn(listOfAnswers);

        List<Answer> returnedListOfAnswers = (List<Answer>) answerService.getAllAnswers();
        verify(mockedAnswerRepo).findAll();

        assert returnedListOfAnswers.equals(listOfAnswers);
    }

    @Test
    public void findAnswersAnsweredByUser() {
        Answer[] answersByGavin = {answer};
        Answer[] answersByEmma = {};

        User gavin = new User("Gavin");
        User emma = new User("Emma");

        when(mockedAnswerRepo.findAnswersByUserUsername("Gavin")).thenReturn(answersByGavin);
        when(mockedAnswerRepo.findAnswersByUserUsername("Emma")).thenReturn(answersByEmma);

        Answer[] returnedAnswersForGavin = answerService.findAnswersAnsweredByUser(gavin);
        verify(mockedAnswerRepo).findAnswersByUserUsername("Gavin");

        Answer[] returnedAnswersForEmma = answerService.findAnswersAnsweredByUser(emma);
        verify(mockedAnswerRepo).findAnswersByUserUsername("Emma");

        assert answersByEmma.equals(returnedAnswersForEmma);
        assert answersByGavin.equals(returnedAnswersForGavin);
    }

    @Test
    public void findAnswersAnsweredByUsername() {
        Answer[] answersByGavin = {answer};
        Answer[] answersByEmma = {};

        when(mockedAnswerRepo.findAnswersByUserUsername("Gavin")).thenReturn(answersByGavin);
        when(mockedAnswerRepo.findAnswersByUserUsername("Emma")).thenReturn(answersByEmma);

        Answer[] returnedAnswersForGavin = answerService.findAnswersAnsweredByUsername("Gavin");
        verify(mockedAnswerRepo).findAnswersByUserUsername("Gavin");

        Answer[] returnedAnswersForEmma = answerService.findAnswersAnsweredByUsername("Emma");
        verify(mockedAnswerRepo).findAnswersByUserUsername("Emma");

        assert answersByEmma.equals(returnedAnswersForEmma);
        assert answersByGavin.equals(returnedAnswersForGavin);
    }
}