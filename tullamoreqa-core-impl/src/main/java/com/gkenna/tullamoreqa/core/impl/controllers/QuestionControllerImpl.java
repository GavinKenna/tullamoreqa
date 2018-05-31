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
    public Question getQuestion(@PathVariable Long questionId) {
        return questionService.getQuestion(questionId);
    }

    @Override
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question input) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return null;
    }
}
