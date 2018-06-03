/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Answer;
import org.springframework.http.ResponseEntity;

public interface AnswerController {
    /**
     * POST Method
     *
     * @param input
     * @return
     */
    ResponseEntity<?> addAnswer(Answer input);

    /**
     * GET Method
     *
     * @param answerId
     * @return
     */
    ResponseEntity<Answer> getAnswer(Long answerId);

    /**
     * PUT Method
     * @param answerId
     * @param input
     * @return
     */
    ResponseEntity<?> updateAnswer(Long answerId, Answer input);


    /**
     * DELETE Method
     * @param answerId
     * @return
     */
    ResponseEntity<?> deleteAnswer(Long answerId);
}
