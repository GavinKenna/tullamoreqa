package com.gkenna.tullamoreqa.domain.service;

import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.service.api.CommentService;

public class CommentServiceImpl implements CommentService {
    @Override
    public void addComment(Comment Comment) {
    }

    @Override
    public void deleteComment(Comment Comment) {

    }

    @Override
    public void deleteComment(long id) {

    }

    @Override
    public void editComment(Comment Comment) {

    }

    @Override
    public boolean doesCommentExist(Comment Comment) {
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
