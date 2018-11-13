/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Vote;


/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Entry}
 * subclasses, i.e.{@link com.gkenna.tullamoreqa.domain.Answer},
 * {@link com.gkenna.tullamoreqa.domain.Comment} and
 * {@link com.gkenna.tullamoreqa.domain.Question}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public interface EntryService {
    /**
     * Cast a new Vote to a particular Entry.
     *
     * TODO Should we just have one implementation of this API? On
     * EntryServiceImpl
     *
     * @param entryId Entry to cast Vote to.
     * @param vote Vote to cast.
     */
    void castVote(final Long entryId, final Vote vote);

    /**
     * Delete Vote from a particular Entry.
     *
     * TODO Should we just have one implementation of this API? On
     * EntryServiceImpl
     * @param entryId Entry to remove Vote from.
     * @param vote Vote to remove..
     */
    void deleteVote(final Long entryId, final Vote vote);
}
