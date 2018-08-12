/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.EntryNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.EntryService;

public abstract class EntryServiceImpl implements EntryService {
    @Override
    public void addUpvote(Long entryId) throws EntryNotFoundException {

    }

    @Override
    public void removeUpvote(Long entryId) throws EntryNotFoundException {

    }

    @Override
    public void addDownvote(Long entryId) throws EntryNotFoundException {

    }

    @Override
    public void removeDownvote(Long entryId) throws EntryNotFoundException {

    }
}
