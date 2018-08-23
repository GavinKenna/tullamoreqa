/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.services;

import com.gkenna.tullamoreqa.core.api.exceptions.QuestionNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.core.impl.Application;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionServiceIT {
    private static final Logger LOGGER = LogManager.getLogger(QuestionServiceIT.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    private Set<Tag> tags;

    private User user;

    private User modifiedByUser;

    private Date lastUpdatedAt;

    private Date createdAt;

    private Question validQuestion;

    private Question invalidQuestion;

    @Before
    public void setup() {
        tags = new HashSet<>();
        tags.add(new Tag("QuestionServiceIT_Tag"));
        user = new User("QuestionServiceIT_Username");
        modifiedByUser = new User("QuestionServiceIT_ModUser");
        lastUpdatedAt = Date.from(Instant.EPOCH);
        createdAt = Date.from(Instant.EPOCH);

        tagRepository.saveAll(tags);
        tagRepository.flush();
        userRepository.saveAndFlush(user);
        userRepository.saveAndFlush(modifiedByUser);
    }

    @Test
    @Transactional
    public void addValidQuestion() {
        validQuestion = new Question();
        validQuestion.setUpvotes(0);
        validQuestion.setDownvotes(0);
        validQuestion.setTags(tags);
        validQuestion.setModifiedBy(user);
        validQuestion.setModifiedBy(modifiedByUser);
        validQuestion.setLastUpdatedAt(lastUpdatedAt);
        validQuestion.setCreatedAt(createdAt);
        validQuestion.setTitle("Question Title");
        validQuestion.setBody("Question Body");

        questionService.addQuestion(validQuestion);
        Question fromRepo = questionRepository.getOne(validQuestion.getId());

        assert fromRepo != null;
        assert fromRepo.equals(validQuestion);
    }

    @Test
    @Transactional
    public void shouldReturnListOfQuestionsBasedOnTag() {
        List<Question> questionList = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Test1"));
        tags.add(new Tag("Test2"));
        tags.add(new Tag("Test3"));
        for (Tag t : tags) {
            tagRepository.saveAndFlush(t);
        }

        Question question1 = new Question();
        question1.addTag(tagRepository.getOne("Test1"));
        question1.addTag(tagRepository.getOne("Test2"));

        Question question2 = new Question();
        question2.addTag(tagRepository.getOne("Test1"));
        question2.addTag(tagRepository.getOne("Test3"));

        Question question3 = new Question();
        question3.addTag(tagRepository.getOne("Test2"));

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);

        for (Question q : questionList) {
            questionRepository.saveAndFlush(q);
        }

        Page<Question> questions = questionRepository.findAllByTagsName("Test1", Pageable.unpaged());

        List<Question> returnedQuestions = questions.getContent();
        assert returnedQuestions.contains(question1);
        assert returnedQuestions.contains(question2);
        assert !returnedQuestions.contains(question3);

    }

    @Test
    @Transactional
    public void shouldDeleteValidQuestionByIdSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setUpvotes(0);
        question.setDownvotes(0);
        question.setTags(tags);
        question.setModifiedBy(user);
        question.setModifiedBy(modifiedByUser);
        question.setLastUpdatedAt(lastUpdatedAt);
        question.setCreatedAt(createdAt);
        question.setTitle("Question Title");
        question.setBody("Question Body");
        question.setTitle("I want to be deleted please.");

        final Long id = questionRepository.save(question).getId();

        assert questionRepository.existsById(id);

        questionService.deleteQuestion(id);

        assert !questionRepository.existsById(id);
    }

    @Test
    @Transactional
    public void shouldDeleteValidQuestionByReferenceSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setTitle("I want to be deleted please.");

        final Long id = questionRepository.save(question).getId();

        assert questionRepository.existsById(id);

        final Question reference = questionRepository.findById(id).get();

        questionService.deleteQuestion(reference);

        assert !questionRepository.existsById(id);
    }

    @Test
    @Transactional
    public void shouldDeleteValidQuestionByOriginalReferenceSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setTitle("I want to be deleted please.");

        Long id = questionRepository.save(question).getId();

        assert questionRepository.existsById(question.getId());

        questionService.deleteQuestion(question);

        assert !questionRepository.existsById(question.getId());
        assert !questionRepository.existsById(id);
    }

    @Test(expected = QuestionNotFoundException.class)
    @Transactional
    public void shouldThrowExceptionWhenTryingToDeleteValidQuestionByIdSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setUpvotes(0);
        question.setDownvotes(0);
        question.setTags(tags);
        question.setModifiedBy(user);
        question.setModifiedBy(modifiedByUser);
        question.setLastUpdatedAt(lastUpdatedAt);
        question.setCreatedAt(createdAt);
        question.setTitle("Question Title");
        question.setBody("Question Body");
        question.setTitle("I want to be deleted please.");

        final Long id = questionRepository.save(question).getId();

        assert questionRepository.existsById(id);

        /*
        Remove it before we continue.
         */
        questionRepository.deleteById(id);

        /*
        Should throw a QuestionDoesNotExist exception.
         */
        questionService.deleteQuestion(id);
    }

    @Test(expected = QuestionNotFoundException.class)
    @Transactional
    public void shouldThrowExceptionWhenTryingToDeleteValidQuestionByReferenceSuccessfully() throws QuestionNotFoundException {
        final Question question = new Question();
        question.setTitle("I want to be deleted please.");

        final Long id = questionRepository.save(question).getId();

        assert questionRepository.existsById(id);

        final Question reference = questionRepository.findById(id).get();

        /*
        Remove it before we continue.
         */
        questionRepository.deleteById(id);

        questionService.deleteQuestion(reference);

        assert !questionRepository.existsById(id);
    }

    @Test(expected = QuestionNotFoundException.class)
    @Transactional
    public void shouldThrowExceptionWhenTryingToDeleteValidQuestionByOriginalReferenceSuccessfully() throws
            QuestionNotFoundException {
        final Question question = new Question();
        question.setTitle("I want to be deleted please.");

        Long id = questionRepository.save(question).getId();

        assert questionRepository.existsById(question.getId());

        /*
        Remove it before we continue.
         */
        questionRepository.deleteById(id);

        questionService.deleteQuestion(question);

        assert !questionRepository.existsById(question.getId());
        assert !questionRepository.existsById(id);
    }

    @Test
    @Transactional
    public void shouldPartiallyUpdateQuestionSuccessfully() throws QuestionNotFoundException {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2008, 06, 15);
        final Date createdDate = calendar.getTime();
        calendar.set(2018, 06, 14);
        final Date modifiedAt = calendar.getTime();

        final Question originalQuestion = new Question();
        originalQuestion.setTitle("OriginalTitle");
        originalQuestion.setCreatedBy(user);
        originalQuestion.setModifiedBy(user);
        originalQuestion.setBody("Original Body");
        originalQuestion.setDownvotes(12);
        originalQuestion.setUpvotes(12);
        originalQuestion.setCreatedAt(createdDate);
        originalQuestion.setLastUpdatedAt(modifiedAt);

        final Long id = questionRepository.save(originalQuestion).getId();

        assert questionRepository.existsById(id);

        final Question updatedQuestion = new Question();
        updatedQuestion.setDownvotes(11);

        questionService.updateQuestion(id, updatedQuestion);

        final Question returnQuestion = questionRepository.findById(id).get();
        assert returnQuestion.getDownvotes() == 11;
        assert returnQuestion.getUpvotes() == 12;
        assert returnQuestion.getTitle().equals("OriginalTitle");
        assert returnQuestion.getCreatedAt().equals(createdDate);
        assert returnQuestion.getLastUpdatedAt().equals(modifiedAt);
    }

    @Test
    @Transactional
    public void editInValidQuestion() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void filterQuestions() {
        //Stub
        assert true;
    }

}
