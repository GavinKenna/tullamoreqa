/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService extends EntryService {
    void addAnswer(Answer answer);

    void deleteAnswer(Answer answer);

    Answer deleteAnswer(Long answerId) throws AnswerNotFoundException;

    boolean doesAnswerExist(Answer answer);

    boolean doesAnswerExist(Long answerId);

    Answer getAnswer(Long answerId) throws AnswerNotFoundException;

    Iterable<Answer> getAllAnswers();

    Answer[] findAnswersAnsweredByUser(User user);

    Answer[] findAnswersAnsweredByUsername(String username);

    Answer updateAnswer(Long answerId, Answer input) throws AnswerNotFoundException;

    void addUpvote(Long answerId) throws AnswerNotFoundException;

    void removeUpvote(Long answerId) throws AnswerNotFoundException;

    void addDownvote(Long answerId) throws AnswerNotFoundException;

    void removeDownvote(Long answerId) throws AnswerNotFoundException;
}
