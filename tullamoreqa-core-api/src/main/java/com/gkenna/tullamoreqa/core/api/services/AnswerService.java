package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AnswerService {
    void addAnswer(Answer answer);
    void deleteAnswer(Answer answer);
    void deleteAnswer(long id);
    void editAnswer(Answer answer);
    boolean doesAnswerExist(Answer answer);
    boolean doesAnswerExist(long id);
    Optional<Answer> getAnswer(long id);
    Iterable<Answer> getAllAnswers();
    Answer[] findAnswersAnsweredByUser(User user);
}
