/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Answer}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface AnswerService extends EntryService {
    /**
     * Insert a new {@link com.gkenna.tullamoreqa.domain.Answer} to the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     *
     * @param answer {@link com.gkenna.tullamoreqa.domain.Answer} to Add.
     */
    void addAnswer(final Answer answer);

    /**
     * Delete an {@link com.gkenna.tullamoreqa.domain.Answer} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     *
     * @param answer {@link com.gkenna.tullamoreqa.domain.Answer} to Delete.
     */
    void deleteAnswer(final Answer answer);

    /**
     * Delete an {@link com.gkenna.tullamoreqa.domain.Answer} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     *
     * @param answerId ID of the
     *                 {@link com.gkenna.tullamoreqa.domain.Answer} to delete.
     */
    Answer deleteAnswer(final Long answerId) throws AnswerNotFoundException;

    /**
     * Return if an {@link com.gkenna.tullamoreqa.domain.Answer} exists in the DB
     * or not.
     *
     * @param answer Does this {@link com.gkenna.tullamoreqa.domain.Answer} exist?
     * @return True if the {@link com.gkenna.tullamoreqa.domain.Answer} exists,
     * false if otherwise.
     */
    boolean doesAnswerExist(final Answer answer);

    /**
     * Return if an {@link com.gkenna.tullamoreqa.domain.Answer} exists in the DB
     * or not.
     *
     * @param answerId Does the ID for this
     *                 {@link com.gkenna.tullamoreqa.domain.Answer} exist?
     * @return True if the {@link com.gkenna.tullamoreqa.domain.Answer} exists,
     * false if otherwise.
     */
    boolean doesAnswerExist(final Long answerId);

    /**
     * Return an {@link com.gkenna.tullamoreqa.domain.Answer} based on its ID.
     *
     * @param answerId The ID of the {@link com.gkenna.tullamoreqa.domain.Answer} to
     *                 return.
     * @return An {@link com.gkenna.tullamoreqa.domain.Answer} with the ID passed.
     * @throws AnswerNotFoundException Thrown if the Answer cannot be found.
     */
    Answer getAnswer(final Long answerId) throws AnswerNotFoundException;

    /**
     * Return all {@link com.gkenna.tullamoreqa.domain.Answer}s in the DB.
     *
     * @return All {@link com.gkenna.tullamoreqa.domain.Answer}s in the DB.
     */
    Iterable<Answer> getAllAnswers();

    /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param user Filter all  {@link Answer}s based on this
     *             {@link com.gkenna.tullamoreqa.domain.User}.
     * @return Array of {@link Answer}s.
     */
    Answer[] findAnswersAnsweredByUser(final User user);

    /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param username Filter all  {@link Answer}s based on this
     *                 {@link com.gkenna.tullamoreqa.domain.User} Username.
     * @return Array of {@link Answer}s.
     */
    Answer[] findAnswersAnsweredByUsername(final String username);

    /**
     * Update an {@link Answer} on the Database.
     *
     * @param answerId The ID of the {@link Answer} to update.
     * @param input    An {@link Answer} container that holds
     *                 new values for answerId to update to.
     * @return The {@link Answer} that was updated.
     */
    Answer updateAnswer(final Long answerId,
                        final Answer input) throws AnswerNotFoundException;

}
