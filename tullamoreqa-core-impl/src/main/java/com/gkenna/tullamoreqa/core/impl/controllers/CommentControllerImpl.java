/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.CommentController;
import com.gkenna.tullamoreqa.core.api.exceptions.CommentNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import com.gkenna.tullamoreqa.domain.Comment;
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

import java.math.BigInteger;
import java.net.URI;

/**
 * Implementation of {@link CommentController}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@RestController
@RequestMapping("/comment")
public class CommentControllerImpl implements CommentController {

    /**
     * Comment Controller Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CommentControllerImpl.class);

    /**
     * Comment Service, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the Comment Repo
     * {@link com.gkenna.tullamoreqa.core.api.repositories.CommentRepository}.
     */
    @Autowired
    private CommentService commentService;


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<?> addComment(
            @RequestBody final Comment input) {

        LOGGER.debug("Adding Comment {}", input);

        //TODO Add exception handling
        commentService.addComment(input);
        /*
        Retrieving URI of new Comment.
         */
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(input.getId()).toUri();

        LOGGER.debug("Comment #{} URI location is {}",
                input.getId(), location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public final ResponseEntity<Comment> getComment(
            @PathVariable("id") final BigInteger commentId) {

        LOGGER.debug("Attempting to get Comment {}", commentId);
        Comment output = commentService.getComment(commentId);
        if (output == null) {
            LOGGER.error("Comment with id {} not found.", commentId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Comment with id "
                    + commentId + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public final ResponseEntity<?> updateComment(
            @PathVariable("id") final BigInteger commentId,
            @RequestBody final Comment input) {

        LOGGER.debug("Updating Comment {} with the following details {}",
                commentId, input);

        Comment output;
        try {
            output = commentService.updateComment(commentId, input);
        } catch (CommentNotFoundException e) {
            LOGGER.error("Comment with id {} not found.", commentId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id "
                    + commentId + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public final ResponseEntity<?> deleteComment(
            @PathVariable("id") final BigInteger commentId) {

        LOGGER.debug("Deleting Comment {}", commentId);
        Comment output;
        try {
            output = commentService.deleteComment(commentId);
        } catch (CommentNotFoundException e) {
            LOGGER.error("Comment with id {} not found.", commentId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Comment with id "
                    + commentId + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Comment>(output, HttpStatus.OK);
    }
}
