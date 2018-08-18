/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.EntryNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.EntryService;

import java.math.BigInteger;

/**
 * Implementation of {@link EntryService}.
 *
 * @author Gavin Kenna
 * @see EntryService
 * @since 0.0.8
 */
public abstract class EntryServiceImpl implements EntryService {
    @Override
    public void addUpvote(final BigInteger entryId) throws EntryNotFoundException {

    }

    @Override
    public void removeUpvote(final BigInteger entryId)
            throws EntryNotFoundException {

    }

    @Override
    public void addDownvote(final BigInteger entryId) throws EntryNotFoundException {

    }

    @Override
    public void removeDownvote(final BigInteger entryId)
            throws EntryNotFoundException {

    }
}
