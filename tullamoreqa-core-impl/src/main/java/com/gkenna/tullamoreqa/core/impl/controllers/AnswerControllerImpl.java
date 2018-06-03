/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.AnswerController;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.domain.Answer;
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
@RequestMapping("/answer")
public class AnswerControllerImpl implements AnswerController {

    private static final Logger LOGGER = LogManager.getLogger(AnswerControllerImpl.class);

    @Autowired
    private AnswerService answerService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAnswer(@RequestBody Answer input) {
        LOGGER.debug("Adding Answer {}", input);

        //TODO Add exception handling
        answerService.addAnswer(input);
        /*
        Retrieving URI of new Answer.
         */
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(input.getId()).toUri();

        LOGGER.debug("Answer #{} URI location is {}", input.getId(), location);

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable("id") Long answerId) {
        Answer output = answerService.getAnswer(answerId);
        if (output == null) {
            LOGGER.error("Answer with id {} not found.", answerId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id " + answerId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Answer>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable("id") Long answerId, @RequestBody Answer input) {

        LOGGER.debug("Updating Answer {} with the following details {}", answerId, input);
        Answer output;
        try {
            output = answerService.updateAnswer(answerId, input);
        } catch (Exception e) {
            LOGGER.error("Answer with id {} not found.", answerId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id " + answerId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Answer>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") Long answerId) {
        LOGGER.debug("Deleting Answer {}", answerId);
        Answer output;
        try {
            output = answerService.deleteAnswer(answerId);
        } catch (Exception e) {
            LOGGER.error("Answer with id {} not found.", answerId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id " + answerId
                    + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Answer>(output, HttpStatus.OK);
    }
}
