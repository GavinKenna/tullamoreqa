/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.CommentController;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import com.gkenna.tullamoreqa.domain.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comment")
public class CommentControllerImpl implements CommentController {

    private static final Logger LOGGER = LogManager.getLogger(CommentControllerImpl.class);

    @Autowired
    private CommentService commentService;


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody  Comment input) {
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

        LOGGER.debug("Comment #{} URI location is {}", input.getId(), location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long commentId) {
        LOGGER.debug("Attempting to get Comment {}", commentId);
        Comment output = commentService.getComment(commentId);
        if (output == null) {
            LOGGER.error("Comment with id {} not found.", commentId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Comment with id " + commentId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long commentId, @RequestBody Comment input) {
        LOGGER.debug("Updating Comment {} with the following details {}", commentId, input);
        Comment output;
        try {
            output = commentService.updateComment(commentId, input);
        } catch (Exception e) {
            LOGGER.error("Comment with id {} not found.", commentId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id " + commentId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long commentId) {
        LOGGER.debug("Deleting Comment {}", commentId);
        Comment output;
        try {
            output = commentService.deleteComment(commentId);
        } catch (Exception e) {
            LOGGER.error("Comment with id {} not found.", commentId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Comment with id " + commentId
                    + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Comment>(output, HttpStatus.OK);
    }
}
