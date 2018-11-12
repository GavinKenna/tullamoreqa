/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.services;

import com.gkenna.tullamoreqa.it.AppConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
public class UserServiceIT {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceIT.class);

    @Test
    @Transactional
    public void addValidUser() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void deleteValidUser() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void deleteInValidUser() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void editValidUser() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void editInValidUser() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void filterUsers() {
        //Stub
        assert true;
    }
}
