package com.gkenna.tullamoreqa.domain.service;


import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public void addQuestion() {
        User u = new User();
        u.setUsername("Gavin");

        userRepository.save(u);

        Question q = new Question();
        q.setQuestionTitle("Help");
        q.setAskedBy(u);

        questionRepository.save(q);
    }

}