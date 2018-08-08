/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.controllers;

import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.core.api.services.UserService;
import com.gkenna.tullamoreqa.core.impl.Application;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import sun.util.calendar.BaseCalendar;

import java.time.Instant;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswerControllerIT {

    private static final Logger LOGGER = LogManager.getLogger(AnswerControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository qr;

    private Answer validAnswer;

    private Question mockedQuestion;

    private User mockedUser;

    private Set<Tag> tags;

    @Before
    public void setup(){
        Tag tag = new Tag("TAG");
        tags = new HashSet<>();
        tags.add(tag);
        tagRepository.save(tag);
        mockedUser = new User("Emma");
        userRepository.save(mockedUser);
        mockedQuestion = new Question();
        mockedQuestion.setBody("MockedBody");
        mockedQuestion.setTitle("MockedTitle");
        mockedQuestion.setUser(mockedUser);
        mockedQuestion.setCreatedAt(Date.from(Instant.EPOCH));
        mockedQuestion.setLastUpdatedAt(Date.from(Instant.EPOCH));
        mockedQuestion.setModifiedBy(mockedUser);
        mockedQuestion.setTags(tags);
        mockedQuestion.setDownvotes(0);
        mockedQuestion.setUpvotes(0);
        questionRepository.save(mockedQuestion);

        qr.findAll();

    }

    @Test
    public void addAnswerTest() throws Exception {
        validAnswer = new Answer(mockedQuestion, mockedUser, "AddAnswerTest");

        LOGGER.info("ValidAnswer is {}", validAnswer);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                "http://localhost:" + this.port + "/answer", validAnswer, Map.class);

        assert (entity.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    public void doNothing(){
        assert (true == true);
    }

    @Test
    public void getAnswerTest() throws Exception {
        validAnswer = new Answer(mockedQuestion, mockedUser, "GetAnswerTest");

        LOGGER.info("ValidAnswer is {}", validAnswer);

        Long id = answerRepository.save(validAnswer).getId();

        LOGGER.info("ID for getAnswerTest is {}", id);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/answer/"+id, Map.class);

        LOGGER.info("Answer Entity {}", entity.toString());

        assert (entity.getStatusCode() == HttpStatus.OK);
    }

}
