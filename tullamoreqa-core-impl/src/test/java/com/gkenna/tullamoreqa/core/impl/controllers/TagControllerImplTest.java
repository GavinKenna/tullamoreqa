/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagControllerImplTest {

    private static final Logger LOGGER =
            LogManager.getLogger(TagControllerImplTest.class);

    @InjectMocks
    private final TagControllerImpl tagController;

    @Mock
    private TagService mockedTagService;

    @Mock
    private RequestAttributes attrs;

    public TagControllerImplTest() {
        MockitoAnnotations.initMocks(this);
        tagController = new TagControllerImpl(mockedTagService);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/tag");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void shouldAddValidTagWithDescription() throws TagAlreadyExistsException {
        final Tag tag = new Tag("Java");
        tag.setDescription("Description for Java Tag.");

        ResponseEntity responseEntity = tagController.addTag(tag);

        verify(mockedTagService).addTag(tag);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 201; //CREATED

        assert Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString().equals("http://localhost/tag/Java");
    }

    @Test
    public void shouldThrowTagAlreadyExistsWhenAdding() throws TagAlreadyExistsException {
        final Tag tag = new Tag("Java");
        tag.setDescription("Description for Java Tag.");

        /*
        Tag already exists in the DB.
         */
        doThrow(new TagAlreadyExistsException("Mocked Exception")).when(mockedTagService).addTag(tag);

        ResponseEntity responseEntity = tagController.addTag(tag);

        verify(mockedTagService).addTag(tag);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 409; // CONFLICT

        assert Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString().equals("http://localhost/tag/Java");
    }

    @Test
    public void shouldGetTagSuccessfully() throws TagNotFoundException {
        final Tag tag = new Tag("Java");
        tag.setDescription("Description for Java Tag.");

        when(mockedTagService.getTag("Java")).thenReturn(tag);

        ResponseEntity responseEntity = tagController.getTag("Java");

        verify(mockedTagService).getTag("Java");

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 200; // OK

        assert responseEntity.getBody().equals(tag);
    }

    @Test
    public void shouldGetNoContentWhenGetTagDoesNotExist() throws TagNotFoundException {

        doThrow(new TagNotFoundException("Mocked Exception")).when(mockedTagService).getTag("Java");

        ResponseEntity responseEntity = tagController.getTag("Java");

        verify(mockedTagService).getTag("Java");

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }

    @Test
    public void shouldUpdateTagSuccessfully() throws TagNotFoundException {
        final Tag tag = new Tag("Java");
        tag.setDescription("New description for Java Tag.");

        when(mockedTagService.updateTag("Java", tag)).thenReturn(tag);

        ResponseEntity responseEntity = tagController.updateTag("Java", tag);

        verify(mockedTagService).updateTag("Java", tag);

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 200; // OK

        assert responseEntity.getBody().equals(tag);
    }

    @Test
    public void shouldGetNoContentWhenUpdateTagDoesNotExist() throws TagNotFoundException {
        final Tag tag = new Tag("Java");
        tag.setDescription("New description for Java Tag.");

        doThrow(new TagNotFoundException("Mocked Exception")).when(mockedTagService).updateTag("Java", tag);

        ResponseEntity responseEntity = tagController.updateTag("Java", tag);

        verify(mockedTagService).updateTag("Java", tag);

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }

    @Test
    public void shouldDeleteTagSuccessfully() throws TagNotFoundException {
        final Tag tag = new Tag("Java");
        tag.setDescription("Description for Java Tag.");

        ResponseEntity responseEntity = tagController.deleteTag("Java");

        verify(mockedTagService).deleteTag("Java");

        assert responseEntity.getStatusCode().is2xxSuccessful();
        assert responseEntity.getStatusCode().value() == 204; // NO_CONTENT
    }

    @Test
    public void shouldGetNoContentWhenDeleteTagDoesNotExist() throws TagNotFoundException {
        final Tag tag = new Tag("Java");
        tag.setDescription("Description for Java Tag.");

        doThrow(new TagNotFoundException("Mocked Exception")).when(mockedTagService).deleteTag("Java");

        ResponseEntity responseEntity = tagController.deleteTag("Java");

        verify(mockedTagService).deleteTag("Java");

        assert responseEntity.getStatusCode().is4xxClientError();
        assert responseEntity.getStatusCode().value() == 404; // NOT_FOUND
    }

}
