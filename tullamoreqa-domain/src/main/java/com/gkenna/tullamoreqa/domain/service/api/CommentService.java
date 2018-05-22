package com.gkenna.tullamoreqa.domain.service.api;

import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;

public interface CommentService {
    void addComment(Comment Comment);
    void deleteComment(Comment Comment);
    void deleteComment(long id);
    void editComment(Comment Comment);
    boolean doesCommentExist(Comment Comment);
    boolean doesCommentExist(long id);
    Comment getComment(long id);
    Comment[] getAllComments();
    Comment[] findCommentsByUser(User user);
}
