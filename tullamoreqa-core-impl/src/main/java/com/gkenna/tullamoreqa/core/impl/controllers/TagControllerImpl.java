/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.TagController;
import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Implementation of {@link TagController}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@RestController
@RequestMapping("/tag")
public class TagControllerImpl implements TagController {

    /**
     * Tag Controller Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TagControllerImpl.class);

    /**
     * Tag Service, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the Tag Repo
     * {@link com.gkenna.tullamoreqa.core.api.repositories.TagRepository}.
     */
    private final TagService tagService;

    /**
     * Constructor that Auto wires the Tag Service.
     *
     * @param tagService Tag Service implementation.
     */
    @Autowired
    public TagControllerImpl(final TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<?> addTag(@RequestBody final Tag input) {
            LOGGER.debug("Adding Tag {}", input);

        HttpHeaders headers = new HttpHeaders();

        /*
         * Check if a Tag already exists.
         * If so return a 409 and return original Tag.
         */
        try {
            tagService.addTag(input);
        } catch (TagAlreadyExistsException e) {
            LOGGER.error(e);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(input.getName()).toUri();
            headers.setLocation(location);

            return new ResponseEntity<String>(headers, HttpStatus.CONFLICT);
        }

        /*
        Retrieving URI of new Tag.
         */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(input.getName()).toUri();

        LOGGER.debug("Tag #{} URI location is {}", input.getName(), location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public final ResponseEntity<Tag> getTag(
            @PathVariable("id") final String tagId) {

        LOGGER.debug("Attempting to get Tag {}", tagId);

        Tag output;

        try {
            output = tagService.getTag(tagId);
        } catch (TagNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public final ResponseEntity<?> updateTag(
            @PathVariable("id") final String tagId,
            @RequestBody final Tag input) {

        LOGGER.debug("Updating Tag {} with the following details {}",
                tagId, input);

        Tag output;

        try {
            output = tagService.updateTag(tagId, input);
        } catch (TagNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public final ResponseEntity<?> deleteTag(
            @PathVariable("id") final String tagId) {

        LOGGER.debug("Deleting Tag {}", tagId);

        try {
            tagService.deleteTag(tagId);
        } catch (TagNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
    }

    @Override
    public final ResponseEntity<?> patchTag(final String tagId,
                                            final  Tag input) {
        return null;
    }
}
