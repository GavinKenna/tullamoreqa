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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionControllerImpl implements QuestionController {

    private static final Logger LOGGER = LogManager.getLogger(QuestionControllerImpl.class);

    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseEntity<?> addQuestion(@RequestBody Question input) {
        return null;
    }

    @Override
    public Question getQuestion(@PathVariable Long questionId) {
        return null;
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
