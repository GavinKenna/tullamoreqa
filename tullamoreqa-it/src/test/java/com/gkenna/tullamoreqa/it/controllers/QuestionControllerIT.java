/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.controllers;

import com.gkenna.tullamoreqa.core.impl.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerIT {
    private static final Logger LOGGER = LogManager.getLogger(QuestionControllerIT.class);

    @Test
    public void addQuestionTest() {
        //Stub
        assert true;
    }

    @Test
    public void deleteQuestionTest() {
        //Stub
        assert true;
    }

    @Test
    public void editQuestionTest() {
        //Stub
        assert true;
    }

    @Test
    public void filterQuestionTest() {
        //Stub
        assert true;
    }
}
