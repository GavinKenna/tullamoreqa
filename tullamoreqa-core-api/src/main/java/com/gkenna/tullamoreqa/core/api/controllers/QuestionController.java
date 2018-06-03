/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Question;
import org.springframework.http.ResponseEntity;

public interface QuestionController {
    /**
     * POST Method
     *
     * @param input
     * @return
     */
    ResponseEntity<?> addQuestion(Question input);

    /**
     * GET Method
     *
     * @param questionId
     * @return
     */
    ResponseEntity<Question> getQuestion(Long questionId);

    /**
     * PUT Method
     * @param questionId
     * @param input
     * @return
     */
    ResponseEntity<?> updateQuestion(Long questionId, Question input);


    /**
     * DELETE Method
     * @param questionId
     * @return
     */
    ResponseEntity<?> deleteQuestion(Long questionId);
}
