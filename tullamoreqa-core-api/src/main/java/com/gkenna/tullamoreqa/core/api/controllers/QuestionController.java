/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Question;
import org.springframework.http.ResponseEntity;



/**
 * API Controller for the {@link Question} Entity. This API will allow
 * external parties, i.e. UI or CLI, to Get/Add/Update/Delete Questions.
 * <p>
 * The Implementation of this API will in turn call the
 * {@link com.gkenna.tullamoreqa.core.api.services.QuestionService} API.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public interface QuestionController {
    /**
     * HTTP POST Method
     * <p>
     * Add an {@link Question} to the Database.
     *
     * @param input An {@link Question} container that should be inserted into
     *              the Database.
     * @return The Response of this Request.
     */
    ResponseEntity<?> addQuestion(final Question input);

    /**
     * HTTP GET Method
     * <p>
     * Retrieve an {@link Question} from the Database.
     *
     * @param questionId The ID of the {@link Question} to retrieve.
     * @return The Response of this Request.
     */
    ResponseEntity<Question> getQuestion(final Long questionId);

    /**
     * HTTP PUT Method
     * <p>
     * Update an {@link Question} on the Database.
     *
     * @param questionId The ID of the {@link Question} to update.
     * @param input      An {@link Question} container that holds
     *                   new values for questionId to update to.
     * @return The Response of this Request.
     */
    ResponseEntity<?> updateQuestion(final Long questionId,
                                     final Question input);

    /**
     * HTTP DELETE Method.
     * <p>
     * Delete an {@link Question} from the Database.
     *
     * @param questionId The ID of the {@link Question} to delete.
     * @return The Response of this Request.
     */
    ResponseEntity<?> deleteQuestion(final Long questionId);
}
