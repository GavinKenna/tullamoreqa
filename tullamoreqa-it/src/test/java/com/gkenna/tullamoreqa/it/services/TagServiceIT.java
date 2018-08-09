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
public class TagServiceIT {
    private static final Logger LOGGER = LogManager.getLogger(TagServiceIT.class);

    @Test
    @Transactional
    public void addValidTag() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void deleteValidTag() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void deleteInValidTag() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void editValidTag() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void editInValidTag() {
        //Stub
        assert true;
    }

    @Test
    @Transactional
    public void filterTags() {
        //Stub
        assert true;
    }
}
