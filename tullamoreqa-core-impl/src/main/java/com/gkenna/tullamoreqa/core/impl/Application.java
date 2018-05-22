package com.gkenna.tullamoreqa.core.impl;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.gkenna.tullamoreqa.*"})
@ComponentScan({"com.gkenna.tullamoreqa.*"})
@EnableJpaRepositories("com.gkenna.tullamoreqa.domain.repositories")
@EntityScan("com.gkenna.tullamoreqa.domain")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(com.gkenna.tullamoreqa.core.impl.Application.class);
    }

    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;


    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            Iterable<Question> questions = questionService.getAllQuestions();
            questions.toString();
            /* User gavin = new User();
            gavin.setUsername("Gavin");

            userService.addUser(gavin);

            User bob = new User();
            bob.setUsername("Bob");

            userService.addUser(bob);

            User alice = new User();
            alice.setUsername("Alice");

            userService.addUser(alice);

            Question question = new Question();
            question.setBody("Help help help");
            question.setTitle("Help");
            question.setUser(gavin);
            questionService.addQuestion(question);

            Question questionTwo = new Question();
            questionTwo.setBody("HAAAALP");
            questionTwo.setTitle("HALP");
            questionTwo.setUser(alice);
            questionService.addQuestion(questionTwo);

            Answer a = new Answer();
            a.setUser(alice);
            a.setQuestion(question);
            a.setBody("I know the answer!");
            answerService.addAnswer(a);

            Answer b = new Answer();
            b.setUser(bob);
            b.setQuestion(question);
            b.setBody("No, I know it!");
            answerService.addAnswer(b);


            Answer c = new Answer();
            c.setUser(gavin);
            c.setQuestion(questionTwo);
            c.setBody("I don't know anything");
            answerService.addAnswer(c);

            Set<Answer> answers = new HashSet<Answer>();
            answers.add(a);
            answers.add(b);*/

            /*q.setAnswers(answers);

            qq.setAnswers(Collections.singleton(c));

            questionService.addQuestion(new Question());*/
        };
    }


}