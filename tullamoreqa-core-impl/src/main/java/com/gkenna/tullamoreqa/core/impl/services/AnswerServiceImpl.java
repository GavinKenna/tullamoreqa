/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    private static final Logger LOGGER = LogManager.getLogger(AnswerServiceImpl.class);

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void addAnswer(Answer answer) {
        LOGGER.debug("Saving {}", answer);
        answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Answer answer) {
        LOGGER.debug("Deleting {}", answer);
        answerRepository.delete(answer);
    }

    @Override
    public Answer deleteAnswer(Long answerId) throws AnswerNotFoundException {
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
    public Answer updateAnswer(Long answerId, Answer input) throws AnswerNotFoundException {
        LOGGER.debug("Updating {} to {}", answerId, input);
        if (answerRepository.existsById(answerId)) {
            Answer output = answerRepository.getOne(answerId);

            LOGGER.debug("Answer before update {}", output);

            output.setBody(input.getBody());
            output.setQuestion(input.getQuestion());
            output.setUser(input.getUser());
            output.setChosenAnswer(input.isChosenAnswer());
            output.setDownvotes(input.getDownvotes());
            output.setUpvotes(input.getUpvotes());

            LOGGER.debug("Answer after update {}", output);

            answerRepository.save(output);
            return output;
        }
        LOGGER.error("Answer {} does not exist. Cannot delete.", answerId);
        throw new AnswerNotFoundException(answerId + " does not exist.");
    }

    @Override
    public void addUpvote(Long answerId) throws AnswerNotFoundException {
        LOGGER.debug("Attempting to Upvote Answer {}", answerId);
        Answer output;
        try {
            output = answerRepository.getOne(answerId);
        } catch (EntityNotFoundException e) {
            LOGGER.error("Answer not found. Reasoning {}", e.toString());
            throw new AnswerNotFoundException(answerId + " does not exist.");
        }

        output.setUpvotes(output.getUpvotes() + 1);

        LOGGER.debug("Upvoted Answer {} successfully.", answerId);
        answerRepository.save(output);
    }

    @Override
    public void removeUpvote(Long answerId) {

    }

    @Override
    public void addDownvote(Long answerId) {

    }

    @Override
    public void removeDownvote(Long answerId) {

    }

    @Override
    public boolean doesAnswerExist(Answer answer) {
        return this.doesAnswerExist(answer.getId());
    }

    @Override
    public boolean doesAnswerExist(Long answerId) {
        return answerRepository.existsById(answerId);
    }

    @Override
    public Answer getAnswer(Long answerId) throws AnswerNotFoundException {
        LOGGER.debug("Attempting to get Answer {}", answerId);
        try {
            return answerRepository.getOne(answerId);
        } catch (EntityNotFoundException e) {
            LOGGER.error("Answer not found. Reasoning {}", e.toString());
            throw new AnswerNotFoundException(answerId + " does not exist.");
        }
    }

    @Override
    public Iterable<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer[] findAnswersAnsweredByUser(User user) {
        return this.findAnswersAnsweredByUsername(user.getUsername());
    }

    @Override
    public Answer[] findAnswersAnsweredByUsername(String username) {
        return answerRepository.findAnswersByUserUsername(username);
    }

}
