/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.TagController;
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

@RestController
@RequestMapping("/tag")
public class TagControllerImpl implements TagController {

    private static final Logger LOGGER = LogManager.getLogger(TagControllerImpl.class);

    @Autowired
    private TagService tagService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTag(@RequestBody Tag input) {
        LOGGER.debug("Adding Tag {}", input);

        //TODO Add exception handling
        tagService.addTag(input);
        /*
        Retrieving URI of new Tag.
         */
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(input.getName()).toUri();

        LOGGER.debug("Tag #{} URI location is {}", input.getName(), location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable("id") String tagId) {
        LOGGER.debug("Attempting to get Tag {}", tagId);
        Tag output = tagService.getTag(tagId);
        if (output == null) {
            LOGGER.error("Tag with id {} not found.", tagId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Tag with id " + tagId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateTag(@PathVariable("id") String tagId, @RequestBody Tag input) {
        LOGGER.debug("Updating Tag {} with the following details {}", tagId, input);
        Tag output;
        try {
            output = tagService.updateTag(tagId, input);
        } catch (Exception e) {
            LOGGER.error("Tag with id {} not found.", tagId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id " + tagId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable("id") String tagId) {
        LOGGER.debug("Deleting Tag {}", tagId);
        Tag output;
        try {
            output = tagService.deleteTag(tagId);
        } catch (Exception e) {
            LOGGER.error("Tag with id {} not found.", tagId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Tag with id " + tagId
                    + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Tag>(output, HttpStatus.OK);
    }
}