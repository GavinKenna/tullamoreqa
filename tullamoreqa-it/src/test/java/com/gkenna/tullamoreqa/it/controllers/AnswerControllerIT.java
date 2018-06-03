package com.gkenna.tullamoreqa.it.controllers;

import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.impl.Application;
import com.gkenna.tullamoreqa.domain.Answer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswerControllerIT {

    private static final Logger LOGGER = LogManager.getLogger(AnswerControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void addAnswerTest() throws Exception {
        Answer answer = new Answer(null, null, "Body");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                "http://localhost:" + this.port + "/answer", answer, Map.class);

        assert (entity.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    public void getAnswerTest() throws Exception {
        Answer temp = new Answer(null, null, "TEMP");
        Long id = answerRepository.save(temp).getId();

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/answer/"+id, Map.class);

        assert (entity.getStatusCode() == HttpStatus.OK);
    }

}
