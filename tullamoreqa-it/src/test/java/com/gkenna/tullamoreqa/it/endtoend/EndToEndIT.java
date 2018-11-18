/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.endtoend;


import com.gkenna.tullamoreqa.core.api.exceptions.QuestionInvalidException;
import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.core.api.services.UserService;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.it.AppConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
public class EndToEndIT {

    private static final Logger LOGGER = LogManager.getLogger(EndToEndIT.class);

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

    @Test
    public void main() throws QuestionInvalidException {
        createTags();
        createUsers();
        createQuestions();
        createAnswers();

        queryDb();
    }

    private void queryDb() {
        Page<Answer> answersByGavin = answerRepository.findAnswersByUserUsername("Gavin", Pageable.unpaged());
        LOGGER.info("Answer size by Gavin == " + answersByGavin.getTotalPages());
        assert answersByGavin.hasContent();

        Page<Question> questionsByGavin = questionRepository.findQuestionsByCreatedByUsername("Gavin", Pageable.unpaged());
        assert questionsByGavin.hasContent();

        List<Question> questionsByJavaTag = questionRepository.findQuestionsBasedOnAllTagNames
                (new String[]{"Java"}, Pageable.unpaged()).getContent();
        assert questionsByJavaTag.size() == 2;

        List<Question> questionsByJavaAndHelpTag = questionRepository
                .findQuestionsBasedOnAllTagNames(new String[]{"Java", "Help"}, Pageable.unpaged()).getContent();
        assert questionsByJavaAndHelpTag.size() == 1;

    }

    private void createAnswers() {
        Question help = questionRepository.findByTitle("Help", Pageable.unpaged()).iterator().next();
        Question halp = questionRepository.findByTitle("Halp", Pageable.unpaged()).iterator().next();

        assert help != null;
        assert halp != null;

        assert answerRepository.findAll().size() == 0;

        User gavin = userRepository.findByUsername("Gavin");
        User bob = userRepository.findByUsername("Bob");
        User alice = userRepository.findByUsername("Alice");

        Answer a = new Answer(help, alice, "I know the Answer");
        answerService.addAnswer(a);

        assert answerRepository.findAll().size() == 1;

        Answer b = new Answer(help, bob, "No, I know it!");
        answerService.addAnswer(b);

        assert answerRepository.findAll().size() == 2;

        Answer c = new Answer(halp, gavin, "I don't know anything.");
        answerService.addAnswer(c);

        LOGGER.info("Gavins Answer " + c.toString());

        assert answerRepository.findAll().size() == 3;
    }

    private void createQuestions() throws QuestionInvalidException {

        Optional<Tag> java = tagRepository.findById("Java");
        Optional<Tag> help = tagRepository.findById("Help");
        Optional<Tag> some = tagRepository.findById("SomethingNewEntirely");

        List<?> findAllQuestions = questionRepository.findAll();
        assert findAllQuestions.size() == 0;

        Question question = new Question();
        question.setBody("Help help help");
        question.setTitle("Help");
        question.setCreatedBy(userRepository.findByUsername("Gavin"));
        question.getTags().add(java.get());
        questionService.addQuestion(question);

        assert questionRepository.findAll().size() == 1;

        Question questionTwo = new Question();
        questionTwo.setBody("HAAAALP");
        questionTwo.setTitle("Halp");
        questionTwo.setCreatedBy(userRepository.findByUsername("Alice"));
        questionTwo.getTags().add(help.get());
        questionTwo.getTags().add(java.get());
        questionService.addQuestion(questionTwo);

        assert questionRepository.findAll().size() == 2;
    }

    private void createUsers() {
        User gavin = new User("Gavin");

        userService.addUser(gavin);

        User bob = new User("Bob");

        userService.addUser(bob);

        User alice = new User("Alice");

        userService.addUser(alice);

        assert userService.doesUserExist("Gavin") == true;
        assert userService.doesUserExist("Bob") == true;
        assert userService.doesUserExist("Alice") == true;
    }

    //@Transactional
    private void createTags() {
        Tag java = new Tag("Java");
        Tag help = new Tag("Help");
        Tag somethingElse = new Tag("SomethingElse");

        tagRepository.save(java);
        tagRepository.save(help);
        tagRepository.save(somethingElse);

        LOGGER.info("Java Tag exists {}", tagService.doesTagExist("Java"));
        LOGGER.info("All tags {}", tagService.getAllTags());

        assert tagService.doesTagExist("Java") == true;
        assert tagService.doesTagExist("Help") == true;
        assert tagService.doesTagExist("SomethingElse");

    }

}
