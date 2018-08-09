/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.services;

import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.QuestionService;
import com.gkenna.tullamoreqa.core.impl.services.AnswerServiceImpl;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import com.gkenna.tullamoreqa.it.AppConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
public class QuestionServiceIT {
    private static final Logger LOGGER = LogManager.getLogger(QuestionServiceIT.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    private Set<Tag> tags;

    private User user;

    private User modifiedByUser;

    private Date lastUpdatedAt;

    private Date createdAt;

    private Question validQuestion;

    private Question invalidQuestion;

    @Before
    public void setup(){
        tags = new HashSet<>();
        tags.add(new Tag("QuestionServiceIT_Tag"));
        user = new User("QuestionServiceIT_Username");
        modifiedByUser = new User("QuestionServiceIT_ModUser");
        lastUpdatedAt = Date.from(Instant.EPOCH);
        createdAt = Date.from(Instant.EPOCH);

        tagRepository.saveAll(tags);
        tagRepository.flush();
        userRepository.saveAndFlush(user);
        userRepository.saveAndFlush(modifiedByUser);
    }

    @Test
    @Transactional
    public void addValidQuestion(){
        validQuestion = new Question();
        validQuestion.setUpvotes(0);
        validQuestion.setDownvotes(0);
        validQuestion.setTags(tags);
        validQuestion.setModifiedBy(user);
        validQuestion.setModifiedBy(modifiedByUser);
        validQuestion.setLastUpdatedAt(lastUpdatedAt);
        validQuestion.setCreatedAt(createdAt);
        validQuestion.setTitle("Question Title");
        validQuestion.setBody("Question Body");

        questionService.addQuestion(validQuestion);

        //Question fromRepo = questionRepository.getOne(validQuestion.getId());
        Question fromRepo = questionService.getQuestion(validQuestion.getId());

        LOGGER.debug("Question from addValidQuestion is {}", fromRepo);

        LOGGER.debug("Original Question ID is {}",validQuestion.getId());

        assert fromRepo != null;

        assert fromRepo.getBody().equals(validQuestion.getBody());
    }

}
