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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public void deleteQuestion(final Question question)
            throws QuestionNotFoundException {

        this.deleteQuestion(question.getId());
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public void deleteQuestion(final Long questionId)
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
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Question updateQuestion(final Long questionId,
                                   final Question input)
            throws QuestionNotFoundException {

        LOGGER.debug("Updating {} to {}", questionId, input);

        if (questionRepository.existsById(questionId)) {
            final Question output =
                    questionRepository.findById(questionId).get();

            LOGGER.info("Question before update {}", output);

            /*
            TODO How should we update Questions?
            Say if we send a request and only send in an update
            to the description, everything below would be set,
            which could result in nulls.
             */
            output.update(input);

            LOGGER.info("Question after update {}", output);

            questionRepository.saveAndFlush(output);
            return output;
        }

        LOGGER.error("Question {} does not exist. Cannot update.",
                questionId);
        throw new QuestionNotFoundException(questionId + " does not exist.");
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public boolean doesQuestionExist(final Question question) {
        return this.doesQuestionExist(question.getId());
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public boolean doesQuestionExist(final Long questionId) {
        return questionRepository.existsById(questionId);
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Question getQuestion(final Long questionId) throws
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
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Question[] getAllQuestions(final Pageable pageable) {
        return questionRepository.findAll().toArray(new Question[0]);
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Question[] findQuestionsByTitle(final String title,
                                           final Pageable pageable) {

        /*
        TODO Assert Title isn't null, throw exception if it is.
         */
        /*
        TODO Choose strategy on how we Page.
         */
        /*
        TODO All below is temporary until we utilize Pagination correctly.
         */
        final Page<Question> pageableQuestions =
                this.questionRepository.findByTitle(title, Pageable.unpaged());
        return pageableQuestions.getContent().toArray(new Question[0]);
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Question[] findQuestionsAskedByUser(final User user,
                                               final Pageable pageable) {
        /*
        TODO Assert Title isn't null, throw exception if it is.
         */
        /*
        TODO Choose strategy on how we Page.
         */
        /*
        TODO All below is temporary until we utilize Pagination correctly.
         */
        final Page<Question> pageableQuestions =
                this.questionRepository.findQuestionsByCreatedByUsername(
                        user.getUsername(), pageable);
        return pageableQuestions.getContent().toArray(new Question[0]);
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Question[] findQuestionsByTag(final Tag tag,
                                         final Pageable pageable) {
        /*
        TODO Assert Title isn't null, throw exception if it is.
        TODO Choose strategy on how we Page.
        TODO All below is temporary until we utilize Pagination correctly.
         */
        final Page<Question> pageableQuestions =
                this.questionRepository.findAllByTagsName(tag.getId(),
                        Pageable.unpaged());
        return pageableQuestions.getContent().toArray(new Question[0]);
    }

}
