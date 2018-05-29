/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(Comment comment);
    void deleteComment(long id);
    void editComment(Comment comment);
    boolean doesCommentExist(Comment comment);
    boolean doesCommentExist(long id);
    Comment getComment(long id);
    Comment[] getAllComments();
    Comment[] findCommentsByUser(User user);
}
