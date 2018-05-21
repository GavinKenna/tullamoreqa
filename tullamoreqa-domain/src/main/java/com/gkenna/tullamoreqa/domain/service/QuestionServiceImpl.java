package com.gkenna.tullamoreqa.domain.service;


import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.domain.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void addQuestion() {
        User gavin = new User();
        gavin.setUsername("Gavin");

        User bob = new User();
        bob.setUsername("Bob");

        User alice = new User();
        alice.setUsername("Alice");

        userRepository.save(gavin);
        userRepository.save(bob);
        userRepository.save(alice);

        Question q = new Question();
        q.setAskedBy(gavin);

        questionRepository.save(q);

        Answer a = new Answer();
        a.setUser(alice);

        Answer b = new Answer();
        b.setUser(bob);

        Set<Answer> answers = new HashSet<Answer>();
        answers.add(a);
        answers.add(b);


        answerRepository.saveAll(answers);

        q.setAnswers(answers);

        questionRepository.save(q);
    }
}