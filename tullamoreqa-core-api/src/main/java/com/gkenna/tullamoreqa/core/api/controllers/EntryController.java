/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Entry;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.http.ResponseEntity;

/**
 * API Controller for the {@link Entry} Domain. This API will allow
 * external parties, i.e. UI or CLI, to interact with Entry sub-resources,
 * i.e.  {@link com.gkenna.tullamoreqa.domain.Vote}s.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
public interface EntryController {

    /**
     * HTTP POST Method
     * <p>
     * Cast a Vote to the #{@link Entry}. We pass in the #{@link User} casting
     * the vote and whether it is an Upvote or not.
     * If the #{@link User} has already Voted on this #{@link Entry} then we
     * check if the Vote type has changed. If so then we update the Vote type
     * (i.e., Upvote or not).
     * <p>
     * If the Vote type is the same then we delete the Vote.
     *
     * @param userCastingVote The User who is casting the vote.
     * @param isUpvote        If this Vote is an upvote or not (i.e. Downvote).
     * @return The Response of this Request.
     * @since 0.0.11
     */
    ResponseEntity<?> castVote(final User userCastingVote, final boolean isUpvote);
}
