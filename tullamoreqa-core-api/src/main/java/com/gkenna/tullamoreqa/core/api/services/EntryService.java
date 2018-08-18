/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.EntryNotFoundException;

import java.math.BigInteger;

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
     * Increase the {@link com.gkenna.tullamoreqa.domain.Entry} total Upvotes
     * by 1.
     *
     * @param entryId The ID of the {@link com.gkenna.tullamoreqa.domain.Entry}
     *                to update.
     * @throws EntryNotFoundException Thrown if ID of the
     *                                Entry isn't found.
     */
    void addUpvote(final BigInteger entryId) throws EntryNotFoundException;

    /**
     * Decrease the {@link com.gkenna.tullamoreqa.domain.Entry} total Upvotes
     * by 1.
     *
     * @param entryId The ID of the {@link com.gkenna.tullamoreqa.domain.Entry}
     *                to update.
     * @throws EntryNotFoundException Thrown if ID of the
     *                                Entry isn't found.
     */
    void removeUpvote(final BigInteger entryId) throws EntryNotFoundException;

    /**
     * Increase the {@link com.gkenna.tullamoreqa.domain.Entry} total Downvotes
     * by 1.
     *
     * @param entryId The ID of the {@link com.gkenna.tullamoreqa.domain.Entry}
     *                to update.
     * @throws EntryNotFoundException Thrown if ID of the
     *                                Entry isn't found.
     */
    void addDownvote(final BigInteger entryId) throws EntryNotFoundException;

    /**
     * Decrease the {@link com.gkenna.tullamoreqa.domain.Entry} total Downvotes
     * by 1.
     *
     * @param entryId The ID of the {@link com.gkenna.tullamoreqa.domain.Entry}
     *                to update.
     * @throws EntryNotFoundException Thrown if ID of the
     *                                Entry isn't found.
     */
    void removeDownvote(final BigInteger entryId) throws EntryNotFoundException;
}
