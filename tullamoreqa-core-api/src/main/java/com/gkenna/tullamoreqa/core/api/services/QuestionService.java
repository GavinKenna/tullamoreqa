/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Question}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface QuestionService {
    void addQuestion(final Question question);

    void deleteQuestion(final Question question);

    Question deleteQuestion(final Long id);

    Question updateQuestion(final Long questionId, final Question input);

    boolean doesQuestionExist(final Question question);

    boolean doesQuestionExist(final Long id);

    Question getQuestion(final Long id);

    Iterable<Question> getAllQuestions();

    Question[] findQuestionsByTitle(final String title);

    Question[] findQuestionsAskedByUser(final User user);

    Question[] findQuestionsAnsweredByUser(final User user);

    Question[] findQuestionsByTag(final Tag tag);

}
