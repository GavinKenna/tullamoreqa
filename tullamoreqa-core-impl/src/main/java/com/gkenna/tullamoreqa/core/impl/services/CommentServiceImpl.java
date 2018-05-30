/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.CommentRepository;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void addComment(Comment comment) {
        LOGGER.debug("Adding {}", comment);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {

    }

    @Override
    public void deleteComment(long id) {

    }

    @Override
    public void editComment(Comment comment) {

    }

    @Override
    public boolean doesCommentExist(Comment comment) {
        return false;
    }

    @Override
    public boolean doesCommentExist(long id) {
        return false;
    }

    @Override
    public Comment getComment(long id) {
        return null;
    }

    @Override
    public Comment[] getAllComments() {
        return new Comment[0];
    }

    @Override
    public Comment[] findCommentsByUser(User user) {
        return new Comment[0];
    }
}
