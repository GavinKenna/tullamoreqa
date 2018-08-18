/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.CommentNotFoundException;
import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Comment}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.CommentRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface CommentService {
    /**
     * Insert a new {@link Comment} to the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.CommentRepository}.
     *
     * @param comment {@link Comment} to Add.
     */
    void addComment(final Comment comment);

    /**
     * Delete a {@link Comment} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.CommentRepository}.
     *
     * @param comment {@link Comment} to Delete.
     */
    void deleteComment(final Comment comment);

    /**
     * Delete a {@link Comment} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.CommentRepository}.
     *
     * @param commentId ID of the {@link Comment} to Delete.
     * @return Comment that was Deleted.
     * @throws CommentNotFoundException Thrown if the {@link Comment}
     *                                  isn't found.
     */
    Comment deleteComment(final BigInteger commentId)
            throws CommentNotFoundException;

    /**
     * Return if a {@link Comment} exists in the DB or not.
     *
     * @param comment Does this {@link Comment} exist?
     * @return True if the {@link Comment} exists,
     * false if otherwise.
     */
    boolean doesCommentExist(final Comment comment);

    /**
     * Return if a {@link Comment} exists in the DB or not.
     *
     * @param commentId Does the ID for this {@link Comment} exist?
     * @return True if the {@link Comment} exists,
     * false if otherwise.
     */
    boolean doesCommentExist(final BigInteger commentId);

    /**
     * Return a {@link Comment} based on its ID.
     *
     * @param commentId The ID of the {@link Comment} to return.
     * @return A {@link Comment} with the ID passed.
     */
    Comment getComment(final BigInteger commentId);

    /**
     * Return all {@link Comment}s in the DB.
     *
     * @return All {@link Comment}s in the DB.
     */
    Comment[] getAllComments();

    /**
     * Return a list of all {@link Comment}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param user Filter all  {@link Comment}s based on this
     *             {@link com.gkenna.tullamoreqa.domain.User}.
     * @return Array of {@link Comment}s.
     */
    Comment[] findCommentsByUser(final User user);

    /**
     * Update a {@link Comment} on the Database.
     *
     * @param commentId The ID of the {@link Comment} to update.
     * @param input     A {@link Comment} container that holds
     *                  new values for questionId to update to.
     * @return The {@link Comment} that was updated.
     * @throws CommentNotFoundException Thrown when the {@link Comment} cannot
     *                                  be found.
     */
    Comment updateComment(final BigInteger commentId, final Comment input)
            throws CommentNotFoundException;
}
