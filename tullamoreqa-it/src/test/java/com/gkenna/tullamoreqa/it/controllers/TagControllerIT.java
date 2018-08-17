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
import org.springframework.transaction.annotation.Transactional;

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

   /* @Autowired
    private TagService tagService;*/

    private String tagEndpoint;

    @Before
    public void setup() {
        tagEndpoint = "http://localhost:" + this.port + "/tag";

        LOGGER.info("Tag Endpoint is {}", this.tagEndpoint);

        LOGGER.info("TagRepo is {}", tagRepository.toString());
    }

    //@Test
    //@Transactional
    public void shouldAddNewTagSuccessfullyWithoutDescription() throws Exception {

        final Tag tag = new Tag("Numberwang1");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang1");
        //assert !tagService.doesTagExist("Numberwang1");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                tagEndpoint, tag, Map.class);

        tagRepository.flush();

        assert (entity.getStatusCode() == HttpStatus.CREATED);
        assert (entity.getHeaders().getLocation()).toString().equals(tagEndpoint + "/Numberwang1");

        /*
        Assert that Numberwang Tag was added successfully.
         */
        assert (tagRepository.existsById("Numberwang1"));
        //assert (tagService.doesTagExist("Numberwang1"));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang1");

        tagRepository.flush();
    }

    //@Test
    //@Transactional
    public void shouldAddNewTagSuccessfullyWithDescription() throws Exception {

        final Tag tag = new Tag("Numberwang2");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang2");
        //assert !tagService.doesTagExist("Numberwang2");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                tagEndpoint, tag, Map.class);

        tagRepository.flush();

        assert (entity.getStatusCode() == HttpStatus.CREATED);
        assert (entity.getHeaders().getLocation()).toString().equals(tagEndpoint + "/Numberwang2");

        /*
        Assert that Numberwang Tag was added successfully.
         */
        assert (tagRepository.existsById("Numberwang2"));
        //assert (tagService.doesTagExist("Numberwang2"));

        assert(tagRepository.getOne("Numberwang2").getDescription().equals("The Tag for Numberwang Questions."));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang2");

        tagRepository.flush();
    }

    @Test
    @Transactional
    public void shouldGetTagSuccessfully() throws Exception {

        final Tag tag = new Tag("Numberwang3");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang3");
        //assert !tagService.doesTagExist("Numberwang3");

        /*
        Inject our Tag into the repo before we try retrieve it
        via the Controller.
         */
        //tagRepository.saveAndFlush(tag);


        this.testRestTemplate.postForEntity(
                tagEndpoint, tag, Map.class);

        tagRepository.flush();

        assert tagRepository.existsById("Numberwang3");

        Tag t = tagRepository.getOne("Numberwang3");

        if(t != null){
            LOGGER.info("Tag is {}", t);

            assert t.equals(tag);
        }

        assert(tagRepository.existsById("Numberwang3"));

        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.getForEntity(
                tagEndpoint+"/Numberwang3", Tag.class);

        LOGGER.info("Entity is {}", entity.toString());

        assert (entity.getStatusCode() == HttpStatus.OK);
        assert (entity.getBody().getName().equals("Numberwang3"));
        assert (entity.getBody().getDescription().equals("The Tag for Numberwang Questions."));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang3");

        tagRepository.flush();
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
