/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Vote;
import org.springframework.http.ResponseEntity;

/**
 * API Controller for the {@link com.gkenna.tullamoreqa.domain.Entry} Domain.
 * This API will allow
 * external parties, i.e. UI or CLI, to interact with Entry sub-resources,
 * i.e.  {@link com.gkenna.tullamoreqa.domain.Vote}s.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
public interface EntryController {

    /**
     * HTTP PUT Method
     * <p>
     * Cast a Vote to the #{@link com.gkenna.tullamoreqa.domain.Entry}.
     * We pass in the #{@link com.gkenna.tullamoreqa.domain.User} casting
     * the vote and whether it is an Upvote or not.
     *
     * @param entryId Id of the Entry we wish to vote on.
     * @param vote The #{@link Vote} entity, be it upvote or downvote.
     * @return The Response of this Request.
     * @since 0.0.11
     */
    ResponseEntity<?> castVote(final Long entryId, final Vote vote);

    /**
     * HTTP DELETE Method
     * <p>
     * Delete a Vote to the #{@link com.gkenna.tullamoreqa.domain.Entry}.
     *
     * @param entryId Id of the Entry we wish to delete the vote from.
     * @param vote The #{@link Vote} entity to delete.
     * @return The Response of this Request.
     * @since 0.0.11
     */
    ResponseEntity<?> deleteVote(final Long entryId, final Vote vote);
}
