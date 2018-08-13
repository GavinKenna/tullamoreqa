/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.http.ResponseEntity;

/**
 * API Controller for the {@link Tag} Entity. This API will allow
 * external parties, i.e. UI or CLI, to Get/Add/Update/Delete Tags.
 * <p>
 * The Implementation of this API will in turn call the
 * {@link com.gkenna.tullamoreqa.core.api.services.TagService} API.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public interface TagController {
    /**
     * HTTP POST Method
     * <p>
     * Add an {@link Tag} to the Database.
     *
     * @param input An {@link Tag} container that should be inserted into
     *              the Database.
     * @return The Response of this Request.
     */
    ResponseEntity<?> addTag(final Tag input);

    /**
     * HTTP GET Method
     * <p>
     * Retrieve an {@link Tag} from the Database.
     *
     * @param tagId The ID of the {@link Tag} to retrieve.
     * @return The Response of this Request.
     */
    ResponseEntity<Tag> getTag(final String tagId);

    /**
     * HTTP PUT Method
     * <p>
     * Update an {@link Tag} on the Database.
     *
     * @param tagId The ID of the {@link Tag} to update.
     * @param input An {@link Tag} container that holds
     *              new values for tagId to update to.
     * @return The Response of this Request.
     */
    ResponseEntity<?> updateTag(final String tagId, final Tag input);

    /**
     * HTTP DELETE Method.
     * <p>
     * Delete an {@link Tag} from the Database.
     *
     * @param tagId The ID of the {@link Tag} to delete.
     * @return The Response of this Request.
     */
    ResponseEntity<?> deleteTag(final String tagId);
}
