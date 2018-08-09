/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO Remove automatic ID generation - should do it in service impl.
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOGGER = LogManager.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void addQuestion(Question question) {
        LOGGER.debug("Adding new Question {}", question);
        questionRepository.saveAndFlush(question);
        LOGGER.info("New Question with ID {} added successfully.", question.getId());
    }

    @Override
    public void deleteQuestion(Question question) {
    }

    @Override
    public Question deleteQuestion(long id) {
        return null;
    }

    @Override
    public Question updateQuestion(Long questionId, Question input) {
        return null;
    }

    @Override
    public boolean doesQuestionExist(Question question) {
        return false;
    }

    @Override
    public boolean doesQuestionExist(long id) {
        return false;
    }

    @Override
    public Question getQuestion(long id) {
        return questionRepository.getOne(id);
    }

    @Override
    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question[] findQuestionsByTitle(String title) {
        return new Question[0];
    }

    @Override
    public Question[] findQuestionsAskedByUser(User user) {
        return new Question[0];
    }

    @Override
    public Question[] findQuestionsAnsweredByUser(User user) {
        return new Question[0];
    }

    @Override
    public Question[] findQuestionsByTag(Tag tag) {
        return new Question[0];
    }
}