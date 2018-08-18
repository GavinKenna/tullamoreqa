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
import org.springframework.transaction.annotation.Transactional;



/**
 * Implementation of {@link QuestionService}.
 *
 * @author Gavin Kenna
 * @see QuestionService
 * @since 0.0.0
 */
@Service("questionService")
@Transactional
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
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public void addQuestion(final Question question) {

        LOGGER.debug("Adding New Question {}", question);

        questionRepository.saveAndFlush(question);

        LOGGER.debug("New Question {} added successfully.", question.getId());
    }

    @Override
    public final void deleteQuestion(final Question question)
            throws QuestionNotFoundException {

        this.deleteQuestion(question.getId());
    }

    @Override
    public final void deleteQuestion(final Long questionId)
            throws QuestionNotFoundException {

        LOGGER.debug("Deleting Question with ID {}", questionId);

        if (this.doesQuestionExist(questionId)) {
            questionRepository.deleteById(questionId);
            return;
        }

        throw new QuestionNotFoundException("Question " + questionId
                + " does not exist.");
    }

    @Override
    public final Question updateQuestion(final Long questionId,
                                         final Question input)
            throws QuestionNotFoundException {

        LOGGER.debug("Updating {} to {}", questionId, input);

        if (questionRepository.existsById(questionId)) {
            final Question output =
                    questionRepository.findById(questionId).get();

            LOGGER.debug("Question before update {}", output);

            output.setBody(input.getBody());
            output.setTitle(input.getTitle());
            output.setCreatedAt(input.getCreatedAt());
            output.setLastUpdatedAt(input.getLastUpdatedAt());
            output.setModifiedBy(input.getModifiedBy());
            output.setTags(input.getTags());
            output.setUser(input.getUser());
            output.setDownvotes(input.getDownvotes());
            output.setUpvotes(input.getUpvotes());

            LOGGER.debug("Question after update {}", output);

            questionRepository.saveAndFlush(output);
            return output;
        }

        LOGGER.error("Question {} does not exist. Cannot update.",
                questionId);
        throw new QuestionNotFoundException(questionId + " does not exist.");
    }

    @Override
    public final boolean doesQuestionExist(final Question question) {
        return this.doesQuestionExist(question.getId());
    }

    @Override
    public final boolean doesQuestionExist(final Long questionId) {
        return questionRepository.existsById(questionId);
    }

    @Override
    public final Question getQuestion(final Long questionId) throws
            QuestionNotFoundException {
        LOGGER.info("QuestionRepo is {}", questionRepository.toString());

        /*
        TODO replace doesExist with Optional<Question> get
         */
        if (this.doesQuestionExist(questionId)) {
            return questionRepository.findById(questionId).get();
        }

        LOGGER.error("Question {} does not exist. Cannot retrieve.",
                questionId);
        throw new QuestionNotFoundException(questionId + " does not exist.");
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
