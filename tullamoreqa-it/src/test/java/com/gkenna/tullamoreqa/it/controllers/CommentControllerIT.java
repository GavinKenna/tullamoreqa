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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerIT {
    private static final Logger LOGGER = LogManager.getLogger(CommentControllerIT.class);

    @Test
    public void addCommentTest() {
        //Stub
        assert true;
    }

    @Test
    public void deleteCommentTest() {
        //Stub
        assert true;
    }

    @Test
    public void editCommentTest() {
        //Stub
        assert true;
    }

    @Test
    public void filterCommentTest() {
        //Stub
        assert true;
    }
}
