/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.QuestionNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link QuestionService}.
 *
 * @author Gavin Kenna
 * @see QuestionService
 * @since 0.0.0
 */
@Service("questionService")
public class QuestionServiceImpl extends EntryServiceImpl
        implements QuestionService {

    /**
     * Question Service Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(QuestionServiceImpl.class);

    /**
     * QuestionRepository, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the DB.
     * We will use this object to Add/Delete/Update/Get {@link Question}.
     */
    private final QuestionRepository questionRepository;

    /**
     * Constructor that Auto wires the Question Repository.
     *
     * @param questionRepository QuestionRepo object.
     */
    @Autowired
    public QuestionServiceImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public final void addQuestion(final Question question) {
        LOGGER.debug("Adding new Question {}", question);
        questionRepository.saveAndFlush(question);
        LOGGER.info("New Question with ID {} added successfully.",
                question.getId());
    }

    @Override
    public final void deleteQuestion(final Question question) {
    }

    @Override
    public final Question deleteQuestion(final Long questionId)
            throws QuestionNotFoundException {
        return null;
    }

    @Override
    public final Question updateQuestion(final Long questionId,
                                         final Question input) {
        return null;
    }

    @Override
    public final boolean doesQuestionExist(final Question question) {
        return false;
    }

    @Override
    public final boolean doesQuestionExist(final Long questionId) {
        return false;
    }

    @Override
    public final Question getQuestion(final Long questionId) {
        return questionRepository.getOne(questionId);
    }

    @Override
    public final Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public final Question[] findQuestionsByTitle(final String title) {
        return new Question[0];
    }

    @Override
    public final Question[] findQuestionsAskedByUser(final User user) {
        return new Question[0];
    }

    @Override
    public final Question[] findQuestionsAnsweredByUser(final User user) {
        return new Question[0];
    }

    @Override
    public final Question[] findQuestionsByTag(final Tag tag) {
        return new Question[0];
    }
}
