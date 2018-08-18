/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

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
     * Insert a new {@link Answer} to the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     *
     * @param answer {@link Answer} to Add.
     */
    void addAnswer(final Answer answer);

    /**
     * Delete an {@link Answer} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     *
     * @param answer {@link Answer} to Delete.
     * @throws AnswerNotFoundException Thrown if {@link Answer}
     *                                 cannot be found.
     */
    void deleteAnswer(final Answer answer) throws AnswerNotFoundException;

    /**
     * Delete an {@link Answer} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     *
     * @param answerId ID of the {@link Answer} to delete.
     * @return Answer that was Deleted.
     * @throws AnswerNotFoundException Thrown if {@link Answer} isn't found.
     */
    Answer deleteAnswer(final BigInteger answerId)
            throws AnswerNotFoundException;

    /**
     * Return if an {@link Answer} exists in the DB or not.
     *
     * @param answer Does this {@link Answer} exist?
     * @return True if the {@link Answer} exists,
     * false if otherwise.
     */
    boolean doesAnswerExist(final Answer answer);

    /**
     * Return if an {@link  Answer} exists in
     * the DB or not.
     *
     * @param answerId Does the ID for this
     *                 {@link Answer} exist?
     * @return True if the {@link Answer} exists,
     * false if otherwise.
     */
    boolean doesAnswerExist(final BigInteger answerId);

    /**
     * Return an {@link Answer} based on its ID.
     *
     * @param answerId The ID of the {@link Answer} to return.
     * @return An {@link Answer} with the
     * ID passed.
     * @throws AnswerNotFoundException Thrown if the {@link Answer}
     *                                 cannot be found.
     */
    Answer getAnswer(final BigInteger answerId) throws AnswerNotFoundException;

    /**
     * Return all {@link Answer}s in the DB.
     *
     * @return All {@link Answer}s in the DB.
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
     * @throws AnswerNotFoundException Thrown when the {@link Answer} cannot
     *                                 be found.
     */
    Answer updateAnswer(final BigInteger answerId,
                        final Answer input) throws AnswerNotFoundException;

}
