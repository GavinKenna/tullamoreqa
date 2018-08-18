/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.AnswerController;
import com.gkenna.tullamoreqa.core.api.exceptions.AnswerNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.domain.Answer;
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
 * Implementation of {@link AnswerController}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@RestController
@RequestMapping("/answer")
public class AnswerControllerImpl implements AnswerController {

    /**
     * Answer Controller Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(AnswerControllerImpl.class);

    /**
     * Answer Service, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the Answer Repo
     * {@link com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository}.
     */
    @Autowired
    private AnswerService answerService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<?> addAnswer(@RequestBody final Answer input) {
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
    public final ResponseEntity<Answer> getAnswer(
            @PathVariable("id") final BigInteger answerId) {

        LOGGER.debug("Attempting to get Answer {}", answerId);
        Answer output;

        try {
            output = answerService.getAnswer(answerId);
        } catch (AnswerNotFoundException e) {
            LOGGER.error("Answer with id {} not found.", answerId);

            return new ResponseEntity(e, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Answer>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public final ResponseEntity<?> updateAnswer(
            @PathVariable("id") final BigInteger answerId,
            @RequestBody final Answer input) {

        LOGGER.debug("Updating Answer {} with the following details {}",
                answerId, input);
        Answer output;
        try {
            output = answerService.updateAnswer(answerId, input);
        } catch (AnswerNotFoundException e) {
            LOGGER.error("Answer with id {} not found.", answerId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id "
                    + answerId + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Answer>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public final ResponseEntity<?> deleteAnswer(
            @PathVariable("id") final BigInteger answerId) {
        LOGGER.debug("Deleting Answer {}", answerId);
        Answer output;
        try {
            output = answerService.deleteAnswer(answerId);
        } catch (AnswerNotFoundException e) {
            LOGGER.error("Answer with id {} not found.", answerId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id "
                    + answerId + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Answer>(output, HttpStatus.OK);
    }
}
