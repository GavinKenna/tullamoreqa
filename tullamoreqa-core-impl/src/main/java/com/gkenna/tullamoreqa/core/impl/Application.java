package com.gkenna.tullamoreqa.core.impl;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.domain.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.domain.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.domain.repositories.TagRepository;
import com.gkenna.tullamoreqa.domain.repositories.UserRepository;
import com.gkenna.tullamoreqa.domain.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication(scanBasePackages = {"com.gkenna.tullamoreqa.*"})
@ComponentScan({"com.gkenna.tullamoreqa.*"})
@EnableJpaRepositories("com.gkenna.tullamoreqa.domain.repositories")
@EntityScan("com.gkenna.tullamoreqa.domain")
@EnableTransactionManagement
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

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TagRepository tagRepository;


    @Bean
    @Transactional
    public CommandLineRunner demo() {
        return (args) -> {


           /* createTags();
            createUsers();
            createQuestions();
            createAnswers();*/

           queryDb();

        };
    }

    private void queryDb() {
       /* Page<Answer> answersByGavin = answerRepository.findByUserId(1L, Pageable.unpaged());
        answersByGavin.forEach(answer -> System.out.println("Answer body by Gavin " + answer.getBody()));

        Page<Answer> answersForQuestion = answerRepository.findByQuestionId(1L, Pageable.unpaged());
        answersForQuestion.forEach(answer -> System.out.println("Answer body " + answer.getBody()));

        Page<Question> questionsByGavin = questionRepository.findByUserId(1L, Pageable.unpaged());
        questionsByGavin.forEach(question -> System.out.println("Question body " + question.getBody()));
*/
        List<String> list = new ArrayList<String>();
        list.add("Java");
        List<Question> questionsByJavaTag = (List<Question>) questionRepository.findByTagsNameContaining("Java");
        questionsByJavaTag.forEach(question -> System.out.println("Question body " + question.getBody()));
//
    }

    private void createAnswers() {
        Question help = questionRepository.findByTitle("Help", Pageable.unpaged()).iterator().next();
        Question halp = questionRepository.findByTitle("Halp", Pageable.unpaged()).iterator().next();

        Answer a = new Answer();
        a.setUser(userRepository.findByUsername("Alice"));
        a.setQuestion(help);
        a.setBody("I know the answer!");
        answerService.addAnswer(a);

        Answer b = new Answer();
        b.setUser(userRepository.findByUsername("Bob"));
        b.setQuestion(help);
        b.setBody("No, I know it!");
        answerService.addAnswer(b);


        Answer c = new Answer();
        c.setUser(userRepository.findByUsername("Gavin"));
        c.setQuestion(halp);
        c.setBody("I don't know anything");
        answerService.addAnswer(c);
    }

    private void createQuestions() {

        Optional<Tag> java = tagRepository.findById("Java");
        Optional<Tag> help = tagRepository.findById("Help");
        Optional<Tag> some = tagRepository.findById("SoemthingElse");

        Question question = new Question();
        question.setBody("Help help help");
        question.setTitle("Help");
        question.setUser(userRepository.findByUsername("Gavin"));
        question.getTags().add(java.get());
        questionService.addQuestion(question);

        Question questionTwo = new Question();
        questionTwo.setBody("HAAAALP");
        questionTwo.setTitle("Halp");
        questionTwo.setUser(userRepository.findByUsername("Alice"));
        questionTwo.getTags().add(help.get());
        questionService.addQuestion(questionTwo);
    }

    private void createUsers() {
        User gavin = new User();
        gavin.setUsername("Gavin");

        userService.addUser(gavin);

        User bob = new User();
        bob.setUsername("Bob");

        userService.addUser(bob);

        User alice = new User();
        alice.setUsername("Alice");

        userService.addUser(alice);
    }

    private void createTags() {
        Tag java = new Tag("Java");
        Tag help = new Tag("Help");
        Tag somethingElse = new Tag("SoemthingElse");

        tagService.addTag(java);
        tagService.addTag(help);
        tagService.addTag(somethingElse);

    }


}