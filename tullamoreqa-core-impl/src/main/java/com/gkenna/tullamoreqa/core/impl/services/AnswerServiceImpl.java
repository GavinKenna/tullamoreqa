/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.Vote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

/**
 * Implementation of {@link AnswerService}.
 *
 * @author Gavin Kenna
 * @see AnswerService
 * @since 0.0.0
 */
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    /**
     * Answer Service Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AnswerServiceImpl.class);

    /**
     * Answer Repository, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the DB.
     * We will use this object to Add/Delete/Update/Get {@link Answer}.
     */
    private final AnswerRepository answerRepository;

    /**
     * Constructor that Auto wires the Answer Repository.
     *
     * @param answerRepository Answer Repo object.
     */
    @Autowired
    public AnswerServiceImpl(final AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public final void addAnswer(final Answer answer) {
        LOGGER.debug("Adding new Answer {}", answer);
        answerRepository.save(answer);
        LOGGER.debug("New Answer with ID {} added successfully.",
                answer.getId());
    }

    @Override
    public final void deleteAnswer(final Answer answer) {
        LOGGER.debug("Deleting {}", answer);
        answerRepository.delete(answer);
    }

    @Override
    public final Answer deleteAnswer(final Long answerId)
            throws AnswerNotFoundException {
        LOGGER.debug("Deleting {}", answerId);
        if (answerRepository.existsById(answerId)) {
            Answer output = answerRepository.getOne(answerId);
            answerRepository.delete(output);
            return output;
        }
        LOGGER.error("Answer {} does not exist. Cannot delete.", answerId);
        throw new AnswerNotFoundException(answerId + " does not exist.");
    }

    @Override
    public final Answer updateAnswer(final Long answerId,
                                     final Answer input)
            throws AnswerNotFoundException {

        LOGGER.debug("Updating {} to {}", answerId, input);
        if (answerRepository.existsById(answerId)) {
            final Answer output = answerRepository.getOne(answerId);

            LOGGER.debug("Answer before update {}", output);

            output.setBody(input.getBody());
            output.setQuestion(input.getQuestion());
            output.setCreatedBy(input.getCreatedBy());
            output.setChosenAnswer(input.isChosenAnswer());

            LOGGER.debug("Answer after update {}", output);

            answerRepository.save(output);
            return output;
        }
        LOGGER.error("Answer {} does not exist. Cannot update.", answerId);
        throw new AnswerNotFoundException(answerId + " does not exist.");
    }

    @Override
    public Answer patchAnswer(String answerId, Answer input) throws AnswerNotFoundException {
        return null;
    }

    @Override
    public final boolean doesAnswerExist(final Answer answer) {
        return this.doesAnswerExist(answer.getId());
    }

    @Override
    public final boolean doesAnswerExist(final Long answerId) {
        return answerRepository.existsById(answerId);
    }

    @Override
    public final Answer getAnswer(final Long answerId)
            throws AnswerNotFoundException {
        LOGGER.debug("Attempting to get Answer {}", answerId);
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isPresent()) {
            return answer.get();
        }

        LOGGER.error("Answer not found.");
        throw new AnswerNotFoundException(answerId + " does not exist.");
    }

    @Override
    public final Iterable<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public final Answer[] findAnswersAnsweredByUser(final User user) {
        return this.findAnswersAnsweredByUsername(user.getUsername());
    }

    @Override
    public final Answer[] findAnswersAnsweredByUsername(final String username) {
        return answerRepository.findAnswersByUserUsername(username);
    }

    @Override
    public final void castVote(final Long entryId, final Vote vote) {

    }

    @Override
    public final void deleteVote(final Long entryId, final Vote vote) {

    }
}
