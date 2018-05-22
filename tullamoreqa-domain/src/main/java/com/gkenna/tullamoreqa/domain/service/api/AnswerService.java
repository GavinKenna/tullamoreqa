package com.gkenna.tullamoreqa.domain.service.api;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;

public interface AnswerService {
    void addAnswer(Answer Answer);
    void deleteAnswer(Answer Answer);
    void deleteAnswer(long id);
    void editAnswer(Answer Answer);
    boolean doesAnswerExist(Answer Answer);
    boolean doesAnswerExist(long id);
    Answer getAnswer(long id);
    Answer[] getAllAnswers();
    Answer[] findAnswersAnsweredByUser(User user);
}
