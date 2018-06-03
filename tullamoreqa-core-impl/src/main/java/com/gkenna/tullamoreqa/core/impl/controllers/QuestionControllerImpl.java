/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.QuestionController;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.domain.Question;
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
@RequestMapping("/question")
public class QuestionControllerImpl implements QuestionController {

    private static final Logger LOGGER = LogManager.getLogger(QuestionControllerImpl.class);

    @Autowired
    private QuestionService questionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addQuestion(@RequestBody Question input) {

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
    public ResponseEntity<Question> getQuestion(@PathVariable("id") Long questionId) {
        LOGGER.debug("Attempting to get Question {}", questionId);
        Question output = questionService.getQuestion(questionId);
        if (output == null) {
            LOGGER.error("Question with id {} not found.", questionId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Question with id " + questionId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Question>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long questionId, @RequestBody Question input) {
        LOGGER.debug("Updating Question {} with the following details {}", questionId, input);
        Question output;
        try {
            output = questionService.updateQuestion(questionId, input);
        } catch (Exception e) {
            LOGGER.error("Question with id {} not found.", questionId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Answer with id " + questionId
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Question>(output, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long questionId) {
        LOGGER.debug("Deleting Question {}", questionId);
        Question output;
        try {
            output = questionService.deleteQuestion(questionId);
        } catch (Exception e) {
            LOGGER.error("Question with id {} not found.", questionId);
            // TODO Replace this exception with custom exception
            return new ResponseEntity(new Exception("Question with id " + questionId
                    + " not found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Question>(output, HttpStatus.OK);
    }
}
