/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.EntryController;
import com.gkenna.tullamoreqa.core.api.services.EntryService;
import com.gkenna.tullamoreqa.domain.Vote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EntryControllerImpl implements EntryController {

    /**
     * Entry Controller Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(EntryControllerImpl.class);

    /**
     * Entry Service, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the Answer Repo
     * {@link com.gkenna.tullamoreqa.core.api.repositories.EntryRepository}.
     */

    @Autowired
    private EntryService entryService;

    @Override
    public ResponseEntity<?> castVote(Long entryId, Vote vote) {
        LOGGER.debug("Casting Vote {} ", vote);
        entryService.castVote(entryId, vote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteVote(Long entryId, Vote vote) {
        LOGGER.debug("Deleting Vote {} ", vote);
        entryService.deleteVote(entryId, vote);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
