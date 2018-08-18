/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.CommentNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.CommentRepository;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Implementation of {@link CommentService}.
 *
 * @author Gavin Kenna
 * @see CommentService
 * @since 0.0.0
 */
@Service("commentService")
public class CommentServiceImpl extends EntryServiceImpl
        implements CommentService {

    /**
     * Comment Service Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CommentServiceImpl.class);

    /**
     * Comment Repository, that will be AutoWired by Spring in the Constructor.
     * This object is used to interact with the DB.
     * We will use this object to Add/Delete/Update/Get {@link Comment}.
     */
    private final CommentRepository commentRepository;

    /**
     * Constructor that Auto wires the Comment Repository.
     *
     * @param commentRepository CommentRepo object.
     */
    @Autowired
    public CommentServiceImpl(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public final void addComment(final Comment comment) {
        LOGGER.debug("Adding new Comment {}", comment);
        commentRepository.save(comment);
        LOGGER.debug("New Comment with ID {} added successfully.",
                comment.getId());
    }

    @Override
    public final void deleteComment(final Comment comment) {
    }

    @Override
    public final Comment deleteComment(final Long commentId)
            throws CommentNotFoundException {
        return null;
    }

    @Override
    public final Comment updateComment(final Long commentId,
                                       final Comment input) {
        return null;
    }

    @Override
    public final boolean doesCommentExist(final Comment comment) {
        return false;
    }

    @Override
    public final boolean doesCommentExist(final Long commentId) {
        return false;
    }

    @Override
    public final Comment getComment(final Long commentId) {
        return commentRepository.getOne(commentId);
    }

    @Override
    public final Comment[] getAllComments() {
        return new Comment[0];
    }

    @Override
    public final Comment[] findCommentsByUser(final User user) {
        return new Comment[0];
    }


}
