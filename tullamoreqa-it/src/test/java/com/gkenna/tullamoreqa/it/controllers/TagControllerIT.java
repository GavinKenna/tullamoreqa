/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.controllers;

import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.core.impl.Application;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagControllerIT {
    private static final Logger LOGGER =
            LogManager.getLogger(TagControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    private String TAG_ENDPOINT;

    @Before
    public void setup() {
        this.TAG_ENDPOINT = "http://localhost:" + this.port + "/tag";
    }

    @Test
    public void shouldAddNewTagSuccessfully() throws Exception {

        final Tag tag = new Tag("Java");
        tag.setDescription("The Tag for Java Questions.");

        /*
        Assert that Java Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Java");
        assert !tagService.doesTagExist("Java");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                TAG_ENDPOINT, tag, Map.class);

        assert (entity.getStatusCode() == HttpStatus.CREATED);
        assert (entity.getHeaders().getLocation()).toString().equals(TAG_ENDPOINT + "/Java");

        /*
        Assert that Java Tag was added successfully.
         */
        assert (tagRepository.existsById("Java"));
        assert (tagService.doesTagExist("Java"));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Java");
    }

    @Test
    public void addTagTest() {
        //Stub
        assert true;
    }

    @Test
    public void deleteTagTest() {
        //Stub
        assert true;
    }

    @Test
    public void editTagTest() {
        //Stub
        assert true;
    }

    @Test
    public void filterTagTest() {
        //Stub
        assert true;
    }
}
