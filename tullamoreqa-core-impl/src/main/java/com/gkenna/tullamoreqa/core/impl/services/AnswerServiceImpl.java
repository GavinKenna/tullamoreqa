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

import java.util.Optional;

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
    public Answer deleteAnswer(Long id) throws AnswerNotFoundException {
        LOGGER.debug("Deleting {}", id);
        if(answerRepository.existsById(id)){
            Answer output = answerRepository.getOne(id);
            answerRepository.delete(output);
            return output;
        }
        LOGGER.error("Answer {} does not exist. Cannot delete.", id);
        throw new AnswerNotFoundException(id + " does not exist.");
    }

    @Override
    public Answer updateAnswer(Long answerId, Answer input) throws AnswerNotFoundException {
        LOGGER.debug("Updating {}", answerId);
        if(answerRepository.existsById(answerId)){
            Answer output = answerRepository.getOne(answerId);
            output.setBody(input.getBody());
            output.setQuestion(input.getQuestion());
            output.setUser(input.getUser());
            output.setChosenAnswer(input.isChosenAnswer());
            output.setDownvotes(input.getDownvotes());
            output.setUpvotes(input.getUpvotes());

            answerRepository.save(output);
            return output;
        }
        LOGGER.error("Answer {} does not exist. Cannot delete.", answerId);
        throw new AnswerNotFoundException(answerId + " does not exist.");
    }

    @Override
    public boolean doesAnswerExist(Answer answer) {
        return this.doesAnswerExist(answer.getId());
    }

    @Override
    public boolean doesAnswerExist(long id) {
        return answerRepository.existsById(id);
    }

    @Override
    public Answer getAnswer(long id) {
        LOGGER.info("ALL ANSWERS {}", answerRepository.findAll());
        Optional<Answer> answer = answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        }
        return null;
    }

    @Override
    public Iterable<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer[] findAnswersAnsweredByUser(User user) {
        return new Answer[0];
    }

}
