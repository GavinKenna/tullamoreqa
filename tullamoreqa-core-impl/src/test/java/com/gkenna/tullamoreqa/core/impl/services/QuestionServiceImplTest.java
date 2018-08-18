/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

//import com.gkenna.tullamoreqa.core.api.exceptions.QuestionAlreadyExistsException;

import com.gkenna.tullamoreqa.core.api.exceptions.QuestionNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {

    private static final Logger LOGGER =
            LogManager.getLogger(QuestionServiceImplTest.class);

    @InjectMocks
    private final QuestionServiceImpl questionService;
    private final Date date = Date.from(Instant.EPOCH);
    @Mock
    private QuestionRepository mockedQuestionRepository;
    @Mock
    private Set<Tag> tags;
    @Mock
    private User user;
    @Mock
    private User modifiedByUser;

    public QuestionServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        questionService = new QuestionServiceImpl(mockedQuestionRepository);
    }

    @Test
    public void shouldAddValidQuestion() {
        final Question question = new Question();

        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        questionService.addQuestion(question);

        verify(mockedQuestionRepository).saveAndFlush(question);
    }

    @Test
    public void shouldAddFullyValidQuestion() {
        final Question question = new Question();
        question.setUpvotes(0);
        question.setDownvotes(0);
        question.setTags(tags);
        question.setModifiedBy(user);
        question.setModifiedBy(modifiedByUser);
        question.setLastUpdatedAt(date);
        question.setCreatedAt(date);
        question.setTitle("Question Title");
        question.setBody("Question Body");

        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        questionService.addQuestion(question);

        verify(mockedQuestionRepository).saveAndFlush(question);
    }


   /* @Test(expected = QuestionAlreadyExistsException.class)
    public void shouldThrowQuestionExistsException() throws QuestionAlreadyExistsException {
        final Question question = new Question();

        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        questionService.addQuestion(question);
    }*/

    @Test
    public void shouldDeleteQuestionByIdSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();

        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        questionService.deleteQuestion(question.getId());

        verify(mockedQuestionRepository).deleteById(question.getId());
    }

    @Test
    public void shouldDeleteQuestionSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        questionService.deleteQuestion(question);

        verify(mockedQuestionRepository).deleteById(question.getId());
    }

    @Test(expected = QuestionNotFoundException.class)
    public void shouldThrowExceptionWhenDeletingQuestionIdThatDoesNotExist() throws QuestionNotFoundException {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        questionService.deleteQuestion(question.getId());

        verify(mockedQuestionRepository).deleteById(question.getId());
    }

    @Test(expected = QuestionNotFoundException.class)
    public void shouldThrowExceptionWhenDeletingQuestionThatDoesNotExist() throws QuestionNotFoundException {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        questionService.deleteQuestion(question);

        verify(mockedQuestionRepository).deleteById(question.getId());
    }

    @Test
    public void shouldUpdateValidQuestionSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();

        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        when(mockedQuestionRepository.findById(question.getId())).thenReturn(java.util.Optional.ofNullable(question));

        final Question updated =
                questionService.updateQuestion(question.getId(), question);

        verify(mockedQuestionRepository).existsById(question.getId());
        verify(mockedQuestionRepository).saveAndFlush(question);

    }

    @Test(expected = QuestionNotFoundException.class)
    public void shouldThrowExceptionWhenUpdating() throws QuestionNotFoundException {
        final Question question = new Question();

        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        when(mockedQuestionRepository.findById(question.getId())).thenReturn(java.util.Optional.ofNullable(question));

        final Question updated =
                questionService.updateQuestion(question.getId(), question);

        verify(mockedQuestionRepository).existsById(question.getId());
        verify(mockedQuestionRepository).saveAndFlush(question);
    }

    @Test
    public void shouldGetQuestionSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        when(mockedQuestionRepository.findById(question.getId())).thenReturn(java.util.Optional.ofNullable(question));

        assert (questionService.getQuestion(question.getId()).equals(question));

        verify(mockedQuestionRepository).findById(question.getId());
    }

    @Test(expected = QuestionNotFoundException.class)
    public void shouldThrowExceptionWhenGettingQuestion() throws QuestionNotFoundException {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        questionService.getQuestion(question.getId());
    }

    @Test
    public void shouldGetAllQuestionsSuccessfully() {
        final Question question = new Question();
        final Question question1 = new Question();
        final Question question2 = new Question();
        final Question question3 = new Question();

        final List<Question> questions = new ArrayList<Question>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        when(mockedQuestionRepository.findAll()).thenReturn(questions);

        questionService.getAllQuestions().iterator();
        List<Question> qs = new ArrayList<>();

        for (Question q : questionService.getAllQuestions()) {
            qs.add(q);
        }

        final Question[] allQuestions = qs.toArray(new Question[0]);

        assert allQuestions[0].equals(question1);
        assert allQuestions[1].equals(question2);
        assert allQuestions[2].equals(question3);

    }


    @Test
    public void shouldReturnTrueForQuestionExistsUsingId() {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        final boolean doesQuestionExist =
                questionService.doesQuestionExist(question.getId());

        verify(mockedQuestionRepository).existsById(question.getId());

        assert doesQuestionExist;
    }


    @Test
    public void shouldReturnTrueForQuestionExists() {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(true);

        final boolean doesQuestionExist = questionService.doesQuestionExist
                (question);

        verify(mockedQuestionRepository).existsById(question.getId());

        assert doesQuestionExist;
    }

    @Test
    public void shouldReturnFalseForQuestionExistsUsingId() {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        final boolean doesQuestionExist = questionService.doesQuestionExist(question.getId());

        verify(mockedQuestionRepository).existsById(question.getId());

        assert !doesQuestionExist;
    }

    @Test
    public void shouldReturnFalseForQuestionExists() {
        final Question question = new Question();
        when(mockedQuestionRepository.existsById(question.getId())).thenReturn(false);

        final boolean doesQuestionExist = questionService.doesQuestionExist(question);

        verify(mockedQuestionRepository).existsById(question.getId());

        assert !doesQuestionExist;
    }
}