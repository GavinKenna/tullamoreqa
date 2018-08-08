/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.EntryNotFoundException;

public interface EntryService {
    void addUpvote(Long entryId) throws EntryNotFoundException;

    void removeUpvote(Long entryId) throws EntryNotFoundException;

    void addDownvote(Long entryId) throws EntryNotFoundException;

    void removeDownvote(Long entryId) throws EntryNotFoundException;
}
