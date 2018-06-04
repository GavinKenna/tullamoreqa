/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    void addAnswer(Answer answer);
    void deleteAnswer(Answer answer);
    Answer deleteAnswer(Long id) throws AnswerNotFoundException;
    boolean doesAnswerExist(Answer answer);
    boolean doesAnswerExist(long id);
    Answer getAnswer(long id);
    Iterable<Answer> getAllAnswers();
    Answer[] findAnswersAnsweredByUser(User user);

    Answer updateAnswer(Long answerId, Answer input) throws AnswerNotFoundException;
}
