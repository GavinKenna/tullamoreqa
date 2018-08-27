/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.controllers;

import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private String tagEndpoint;

    @Before
    public void setup() {
        tagEndpoint = "http://localhost:" + this.port + "/tag";

        LOGGER.info("Tag Endpoint is {}", this.tagEndpoint);
    }

    @Test
    public void shouldAddNewTagSuccessfullyWithoutDescription() throws Exception {

        final Tag tag = new Tag("Numberwang");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                tagEndpoint, tag, Map.class);

        assert (entity.getStatusCode() == HttpStatus.CREATED);
        assert (entity.getHeaders().getLocation()).toString().equals(tagEndpoint + "/Numberwang");

        /*
        Assert that Numberwang Tag was added successfully.
         */
        assert (tagRepository.existsById("Numberwang"));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang");

        tagRepository.flush();
    }

    @Test
    public void shouldGetConflictWhenTryingToAddNewTag() throws Exception {

        final Tag tag = new Tag("Numberwang");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        tagRepository.saveAndFlush(tag);

        assert tagRepository.existsById("Numberwang");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                tagEndpoint, tag, Map.class);

        assert (entity.getStatusCode() == HttpStatus.CONFLICT);
        assert (entity.getHeaders().getLocation()).toString().equals(tagEndpoint + "/Numberwang");

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang");

        tagRepository.flush();
    }

    @Test
    public void shouldAddNewTagSuccessfullyWithDescription() throws Exception {

        final Tag tag = new Tag("Numberwang");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                tagEndpoint, tag, Map.class);

        //tagRepository.flush();

        assert (entity.getStatusCode() == HttpStatus.CREATED);
        assert (entity.getHeaders().getLocation()).toString().equals(tagEndpoint + "/Numberwang");

        /*
        Assert that Numberwang Tag was added successfully.
         */
        assert (tagRepository.existsById("Numberwang"));

        assert (tagRepository.findById("Numberwang").get().getDescription().equals("The Tag for Numberwang Questions."));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang");

        tagRepository.flush();
    }

    @Test
    public void shouldGetTagSuccessfully() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {

        final Tag tag = new Tag("Numberwang");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        /*
        Inject our Tag into the repo before we try retrieve it
        via the Controller.
         */

        tagRepository.saveAndFlush(tag);

        assert tagRepository.existsById("Numberwang");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.getForEntity(
                tagEndpoint + "/Numberwang", Tag.class);

        assert (entity.getStatusCode() == HttpStatus.OK);
        assert (entity.getBody().getName().equals("Numberwang"));
        assert (entity.getBody().getDescription().equals("The Tag for " +
                "Numberwang Questions."));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang");

        tagRepository.flush();
    }

    @Test
    public void shouldGet404WhenTryingToGetInvalidTag() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {
        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.getForEntity(
                tagEndpoint + "/Numberwang", Tag.class);

        assert (entity.getStatusCode() == HttpStatus.NOT_FOUND);

    }

    @Test
    public void shouldDeleteTagSuccessfully() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {

        final Tag tag = new Tag("Numberwang");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        /*
        Inject our Tag into the repo before we try retrieve it
        via the Controller.
         */

        tagRepository.saveAndFlush(tag);

        assert tagRepository.existsById("Numberwang");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> request = new HttpEntity<String>("Empty", headers);

        /*
        TestRestTemplate doesn't return an EntityResponse for Delete, so we must
        use the Exchange method.
         */
        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.exchange(
                tagEndpoint + "/Numberwang", HttpMethod.DELETE, request,
                Tag.class);

        assert (entity.getStatusCode() == HttpStatus.NO_CONTENT); // Deleted

       /*
        Assert that Numberwang Tag doesn't exist.
         */
        assert !tagRepository.existsById("Numberwang");
    }

    @Test
    public void shouldGet404QWhenTringToDeleteInvalidTag() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {
        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> request = new HttpEntity<String>("Empty", headers);

        /*
        TestRestTemplate doesn't return an EntityResponse for Delete, so we must
        use the Exchange method.
         */
        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.exchange(
                tagEndpoint + "/Numberwang", HttpMethod.DELETE, request,
                Tag.class);

        assert (entity.getStatusCode() == HttpStatus.NOT_FOUND); // Deleted
    }

    @Test
    public void shouldUpdateTagDescriptionSuccessfully() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {

        final Tag tag = new Tag("Numberwang");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        /*
        Inject our Tag into the repo before we try retrieve it
        via the Controller.
         */

        tagRepository.saveAndFlush(tag);

        assert tagRepository.existsById("Numberwang");

        tag.setDescription("New description for Numberwang");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Tag> request = new HttpEntity<Tag>(tag, headers);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.exchange(
                tagEndpoint + "/Numberwang", HttpMethod.PUT, request,
                Tag.class);

        LOGGER.info("Update Domain is {}", entity.toString());

        assert (entity.getStatusCode() == HttpStatus.OK);
        assert (entity.getBody().getName().equals("Numberwang"));
        assert (entity.getBody().getDescription().equals("New description for" +
                " Numberwang"));

         /*
        Assert that Numberwang description was updated.
         */
        assert tagRepository.findById("Numberwang").get().getDescription().equals("New description for Numberwang");

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang");

        tagRepository.flush();
    }

    @Test
    public void shouldGet404WhenTryingToUpdateInvalidTag() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        Tag tag = new Tag("Numberwang");
        tag.setDescription("New description!");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Tag> request = new HttpEntity<Tag>(tag, headers);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Tag> entity = this.testRestTemplate.exchange(
                tagEndpoint + "/Numberwang", HttpMethod.PUT, request,
                Tag.class);


        assert (entity.getStatusCode() == HttpStatus.NOT_FOUND);
    }
}
