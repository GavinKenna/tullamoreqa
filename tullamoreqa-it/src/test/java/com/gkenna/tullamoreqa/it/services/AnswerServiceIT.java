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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
public class AnswerServiceIT {
    private static final Logger LOGGER = LogManager.getLogger(AnswerServiceIT.class);

    @Test
    @Transactional
    public void addValidAnswer() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void deleteValidAnswer() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void deleteInValidAnswer() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void editValidAnswer() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void editInValidAnswer() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void filterAnswers() {
        //Stub
        assert true;
    }
}
