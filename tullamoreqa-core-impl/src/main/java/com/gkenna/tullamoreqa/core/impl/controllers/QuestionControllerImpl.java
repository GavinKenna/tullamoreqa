/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.QuestionController;
import com.gkenna.tullamoreqa.domain.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionControllerImpl implements QuestionController {
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
