/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.EntryRepository;
import com.gkenna.tullamoreqa.core.api.services.EntryService;
import com.gkenna.tullamoreqa.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link EntryService}.
 *
 * @author Gavin Kenna
 * @see EntryService
 * @since 0.0.11
 */
@Service("entryService")
public class EntryServiceImpl implements EntryService {

    /**
     * EntryRepository object.
     */
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public final void castVote(final Long entryId, final Vote vote) {

    }

    @Override
    public final void deleteVote(final Long entryId, final Vote vote) {

    }
}
