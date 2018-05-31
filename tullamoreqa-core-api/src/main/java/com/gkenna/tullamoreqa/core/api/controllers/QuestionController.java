/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Question;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface QuestionController {
    /**
     * POST Method
     * @param input
     * @return
     */
    ResponseEntity<?> addQuestion(@RequestBody Question input);

    /**
     * GET Method
     * @param questionId
     * @return
     */
    Question getQuestion(@PathVariable Long questionId);

    ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question input);

    ResponseEntity<?> deleteQuestion(@PathVariable Long questionId);
}
