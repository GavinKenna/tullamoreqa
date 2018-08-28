/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Comment;
import org.springframework.http.ResponseEntity;



/**
 * API Controller for the {@link Comment} Domain. This API will allow
 * external parties, i.e. UI or CLI, to Get/Add/Update/Delete Comments.
 * <p>
 * The Implementation of this API will in turn call the
 * {@link com.gkenna.tullamoreqa.core.api.services.CommentService} API.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public interface CommentController {
    /**
     * HTTP POST Method
     * <p>
     * Add an {@link Comment} to the Database.
     *
     * @param input An {@link Comment} container that should be inserted into
     *              the Database.
     * @return The Response of this Request.
     */
    ResponseEntity<?> addComment(final Comment input);

    /**
     * HTTP GET Method
     * <p>
     * Retrieve an {@link Comment} from the Database.
     *
     * @param commentId The ID of the {@link Comment} to retrieve.
     * @return The Response of this Request.
     */
    ResponseEntity<Comment> getComment(final Long commentId);

    /**
     * HTTP PUT Method
     * <p>
     * Update an {@link Comment} on the Database.
     *
     * @param commentId The ID of the {@link Comment} to update.
     * @param input     An {@link Comment} container that holds
     *                  new values for commentId to update to.
     * @return The Response of this Request.
     */
    ResponseEntity<?> updateComment(final Long commentId,
                                    final Comment input);

    /**
     * HTTP DELETE Method.
     * <p>
     * Delete an {@link Comment} from the Database.
     *
     * @param commentId The ID of the {@link Comment} to delete.
     * @return The Response of this Request.
     */
    ResponseEntity<?> deleteComment(final Long commentId);

    /**
     * HTTP PATCH Method
     * <p>
     * Update an {@link Comment} on the Database.
     *
     * @param commentId The ID of the {@link Comment} to update.
     * @param input An {@link Comment} container that holds
     *              new values for commentId to update to.
     * @since 0.0.11
     * @return The Response of this Request.
     */
    ResponseEntity<?> patchComment(final Long commentId, final Comment input);
}
