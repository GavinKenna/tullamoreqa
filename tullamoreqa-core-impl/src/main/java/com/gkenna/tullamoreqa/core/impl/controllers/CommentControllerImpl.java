/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.CommentController;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import com.gkenna.tullamoreqa.domain.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentControllerImpl implements CommentController {

    private static final Logger LOGGER = LogManager.getLogger(CommentControllerImpl.class);

    @Autowired
    private CommentService commentService;


    @Override
    public ResponseEntity<?> addQuestion(Comment input) {
        return null;
    }

    @Override
    public Comment getComment(Long commentId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateComment(Long commentId, Comment input) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        return null;
    }
}
