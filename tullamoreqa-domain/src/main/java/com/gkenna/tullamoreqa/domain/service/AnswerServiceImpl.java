package com.gkenna.tullamoreqa.domain.service;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.service.api.AnswerService;

public class AnswerServiceImpl implements AnswerService {
    @Override
    public void addAnswer(Answer Answer) {

    }

    @Override
    public void deleteAnswer(Answer Answer) {

    }

    @Override
    public void deleteAnswer(long id) {

    }

    @Override
    public void editAnswer(Answer Answer) {

    }

    @Override
    public boolean doesAnswerExist(Answer Answer) {
        return false;
    }

    @Override
    public boolean doesAnswerExist(long id) {
        return false;
    }

    @Override
    public Answer getAnswer(long id) {
        return null;
    }

    @Override
    public Answer[] getAllAnswers() {
        return new Answer[0];
    }

    @Override
    public Answer[] findAnswersAnsweredByUser(User user) {
        return new Answer[0];
    }
}
