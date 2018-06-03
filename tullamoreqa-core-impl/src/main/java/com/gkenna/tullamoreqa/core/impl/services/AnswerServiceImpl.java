/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Answer deleteAnswer(long id) {
        LOGGER.debug("Deleting {}", id);
        answerRepository.deleteById(id);
        return null;
    }

    @Override
    public Answer updateAnswer(Long answerId, Answer input) {
        return null;
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
        return answerRepository.findById(id).get();
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
