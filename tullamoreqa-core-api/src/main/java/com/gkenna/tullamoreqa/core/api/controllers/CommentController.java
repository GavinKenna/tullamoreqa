/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentController {
    /**
     * POST Method
     *
     * @param input
     * @return
     */
    ResponseEntity<?> addQuestion(Comment input);

    /**
     * GET Method
     *
     * @param commentId
     * @return
     */
    Comment getComment(Long commentId);

    /**
     * PUT Method
     * @param commentId
     * @param input
     * @return
     */
    ResponseEntity<?> updateComment(Long commentId, Comment input);


    /**
     * DELETE Method
     * @param commentId
     * @return
     */
    ResponseEntity<?> deleteComment(Long commentId);
}
