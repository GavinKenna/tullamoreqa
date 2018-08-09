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
public class UserControllerIT {
    private static final Logger LOGGER = LogManager.getLogger(UserControllerIT.class);

    @Test
    public void addUserTest() {
        //Stub
        assert true;
    }

    @Test
    public void deleteUserTest() {
        //Stub
        assert true;
    }

    @Test
    public void editUserTest() {
        //Stub
        assert true;
    }

    @Test
    public void filterUserTest() {
        //Stub
        assert true;
    }
}
