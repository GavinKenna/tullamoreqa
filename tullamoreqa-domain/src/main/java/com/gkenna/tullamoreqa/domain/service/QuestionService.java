package com.gkenna.tullamoreqa.domain.service;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
    void addQuestion(Question question);
    boolean doesQuestionExist(Question question);
    boolean doesQuestionExist(long id);
    Question getQuestion(long id);
    Question[] findQuestionsByTitle(String title);
    Question[] findQuestionsAskedByUser(User user);
    Question[] findQuestionsAnsweredByUser(User user);
}
