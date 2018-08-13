/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TagService}.
 *
 * @author Gavin Kenna
 * @see TagService
 * @since 0.0.0
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    /**
     * Tag Service Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TagServiceImpl.class);

    /**
     * Tag Repository, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the DB.
     * We will use this object to Add/Delete/Update/Get {@link Tag}.
     */
    private final TagRepository tagRepository;

    /**
     * Constructor that Auto wires the Tag Repository.
     *
     * @param tagRepository TagRepo object.
     */
    @Autowired
    public TagServiceImpl(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public final void addTag(final Tag tag) {
        LOGGER.debug("Adding New Tag {}", tag);
        tagRepository.save(tag);
        LOGGER.debug("New Tag {} added successfully.", tag.getName());
    }

    @Override
    public final void deleteTag(final Tag tag) {
    }

    @Override
    public final Tag deleteTag(final String id) {
        return null;
    }

    @Override
    public final Tag updateTag(final String tagId, final Tag input) {
        return null;
    }

    @Override
    public final boolean doesTagExist(final Tag tag) {
        return false;
    }

    @Override
    public final boolean doesTagExist(final String id) {
        return tagRepository.existsById(id);
    }

    @Override
    public final Tag getTag(final String id) {
        return null;
    }

    @Override
    public final Tag[] getAllTags() {
        return new Tag[0];
    }
}
