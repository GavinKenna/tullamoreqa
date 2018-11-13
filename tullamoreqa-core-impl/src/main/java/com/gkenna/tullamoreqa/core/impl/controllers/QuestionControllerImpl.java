/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.QuestionController;
import com.gkenna.tullamoreqa.core.api.exceptions.QuestionNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Vote;
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
 * Implementation of {@link QuestionController}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@RestController
@RequestMapping("/question")
public class QuestionControllerImpl extends EntryControllerImpl
        implements QuestionController {

    /**
     * Question Controller Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(QuestionControllerImpl.class);

    /**
     * Question Service, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the Question Repo
     * {@link com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository}.
     */
    @Autowired
    private QuestionService questionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<?> addQuestion(
            @RequestBody final Question input) {

        LOGGER.info("Add Question : {}", input);
        questionService.addQuestion(input);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(input.getId()).toUri();

        headers.setLocation(location);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public final ResponseEntity<Question> getQuestion(
            @PathVariable("id") final Long questionId) {

        LOGGER.debug("Attempting to get Question {}", questionId);
        Question output;

        try {
            output = questionService.getQuestion(questionId);
        } catch (QuestionNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public final ResponseEntity<?> updateQuestion(
            @PathVariable("id") final Long questionId,
            @RequestBody final Question input) {

        LOGGER.debug("Updating Question {} with the following details {}",
                questionId, input);

        Question output;
        try {
            output = questionService.updateQuestion(questionId, input);
        } catch (QuestionNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public final ResponseEntity<?> patchQuestion(
            @PathVariable("id") final Long questionId,
            @RequestBody final Question input) {

        LOGGER.debug("Patching Question {} with the following details {}",
                questionId, input);

        Question output;
        try {
            output = questionService.patchQuestion(questionId, input);
        } catch (QuestionNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public final ResponseEntity<?> deleteQuestion(
            @PathVariable("id") final Long questionId) {

        LOGGER.debug("Deleting Question {}", questionId);

        try {
            questionService.deleteQuestion(questionId);
        } catch (QuestionNotFoundException e) {
            LOGGER.error(e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/vote")
    public final ResponseEntity<?> castVote(
            @PathVariable("id") final Long entryId,
            @RequestBody final Vote vote) {

        return super.castVote(entryId, vote);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/vote")
    public final ResponseEntity<?> deleteVote(
            @PathVariable("id") final Long entryId,
            @RequestBody final Vote vote) {

        return super.deleteVote(entryId, vote);
    }
}
