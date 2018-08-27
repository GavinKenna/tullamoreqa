/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Answer;
import org.springframework.http.ResponseEntity;



/**
 * API Controller for the {@link Answer} Domain. This API will allow
 * external parties, i.e. UI or CLI, to Get/Add/Update/Delete Answers.
 * <p>
 * The Implementation of this API will in turn call the
 * {@link com.gkenna.tullamoreqa.core.api.services.AnswerService} API.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public interface AnswerController {
    /**
     * HTTP POST Method
     * <p>
     * Add an {@link Answer} to the Database.
     *
     * @param input An {@link Answer} container that should be inserted into
     *              the Database.
     * @return The Response of this Request.
     */
    ResponseEntity<?> addAnswer(final Answer input);

    /**
     * HTTP GET Method
     * <p>
     * Retrieve an {@link Answer} from the Database.
     *
     * @param answerId The ID of the {@link Answer} to retrieve.
     * @return The Response of this Request.
     */
    ResponseEntity<Answer> getAnswer(final Long answerId);

    /**
     * HTTP PUT Method
     * <p>
     * Update an {@link Answer} on the Database.
     *
     * @param answerId The ID of the {@link Answer} to update.
     * @param input    An {@link Answer} container that holds
     *                 new values for answerId to update to.
     * @return The Response of this Request.
     */
    ResponseEntity<?> updateAnswer(final Long answerId,
                                   final Answer input);

    /**
     * HTTP DELETE Method.
     * <p>
     * Delete an {@link Answer} from the Database.
     *
     * @param answerId The ID of the {@link Answer} to delete.
     * @return The Response of this Request.
     */
    ResponseEntity<?> deleteAnswer(final Long answerId);

    /**
     * HTTP PATCH Method
     * <p>
     * Update an {@link Answer} on the Database.
     *
     * @param answerId The ID of the {@link Answer} to update.
     * @param input An {@link Answer} container that holds
     *              new values for answerId to update to.
     * @since 0.0.11
     * @return The Response of this Request.
     */
    ResponseEntity<?> patchTag(final String answerId, final Answer input);
}
