/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link TagService}.
 *
 * @author Gavin Kenna
 * @see TagService
 * @since 0.0.0
 */
@Service("tagService")
@Transactional
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
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public void addTag(final Tag tag) throws TagAlreadyExistsException {
        LOGGER.debug("Adding New Tag {}", tag);

        if (this.doesTagExist(tag.getId())) {
            LOGGER.error("Tag with ID {} already exists!", tag.getId());
            throw new TagAlreadyExistsException(tag.getId()
                    + " already exists.");
        }
        tagRepository.saveAndFlush(tag);

        LOGGER.debug("New Tag {} added successfully.", tag.getName());
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public void deleteTag(final Tag tag) throws TagNotFoundException {
        this.deleteTag(tag.getId());
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public void deleteTag(final String tagId)
            throws TagNotFoundException {

        LOGGER.debug("Deleting Tag with ID {}", tagId);

        if (this.doesTagExist(tagId)) {
            tagRepository.deleteById(tagId);
            return;
        }

        throw new TagNotFoundException("Tag " + tagId
                + " does not exist.");
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Tag updateTag(final String tagId, final Tag input)
            throws TagNotFoundException {

        LOGGER.debug("Updating {} to {}", tagId, input);

        if (tagRepository.existsById(tagId)) {
            final Tag output = tagRepository.findById(tagId).get();

            LOGGER.debug("Tag before update {}", output);

            output.setDescription(input.getDescription());

            LOGGER.debug("Tag after update {}", output);

            tagRepository.saveAndFlush(output);
            return output;
        }

        LOGGER.error("Tag {} does not exist. Cannot update.", tagId);
        throw new TagNotFoundException(tagId + " does not exist.");
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public boolean doesTagExist(final Tag tag) {
        return this.doesTagExist(tag.getId());
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public boolean doesTagExist(final String tagId) {
        return tagRepository.existsById(tagId);
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Tag getTag(final String tagId) throws TagNotFoundException {
        LOGGER.info("TagRepo is {}", tagRepository.toString());
        /*
        TODO replace doesExist with Optional<Tag> get
         */
        if (this.doesTagExist(tagId)) {
            return tagRepository.findById(tagId).get();
        }

        LOGGER.error("Tag {} does not exist. Cannot retrieve.", tagId);
        throw new TagNotFoundException(tagId + " does not exist.");
    }

    @Override
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public Tag[] getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.toArray(new Tag[0]);
    }
}
