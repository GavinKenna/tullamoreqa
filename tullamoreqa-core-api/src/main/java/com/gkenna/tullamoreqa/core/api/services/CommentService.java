/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Comment}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.CommentRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface CommentService {
    void addComment(final Comment comment);

    void deleteComment(final Comment comment);

    Comment deleteComment(final Long id);

    boolean doesCommentExist(final Comment comment);

    boolean doesCommentExist(final Long id);

    Comment getComment(final Long id);

    Comment[] getAllComments();

    Comment[] findCommentsByUser(final User user);

    Comment updateComment(final Long commentId, final Comment input);
}
