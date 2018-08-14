/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger(TagServiceImplTest.class);

    @InjectMocks
    private final TagServiceImpl tagService;

    @Mock
    private TagRepository mockedTagRepository;

    public TagServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        tagService = new TagServiceImpl(mockedTagRepository);
    }

    @Test
    public void shouldAddValidTagWithDescription() throws TagAlreadyExistsException {
        when(mockedTagRepository.existsById("Java")).thenReturn(false);

        final Tag tag = new Tag("Java");
        tag.setDescription("Description for Java Tag.");
        tagService.addTag(tag);

        verify(mockedTagRepository).save(tag);
    }

    @Test
    public void shouldAddValidTagWithoutDescription() throws TagAlreadyExistsException {
        final Tag tag = new Tag("Java");
        tagService.addTag(tag);

        verify(mockedTagRepository).save(tag);
    }

    @Test
    public void shouldAddMultipleValidTagsWithSameDescription() throws TagAlreadyExistsException {
        final Tag tagOne = new Tag("Java");
        final Tag tagTwo = new Tag("Numberwang");
        final Tag tagThree = new Tag("Wangernum");
        final Tag tagFour = new Tag("Emma");
        tagOne.setDescription("Description");
        tagTwo.setDescription("Description");
        tagThree.setDescription("Description");
        tagFour.setDescription("Description");
        tagService.addTag(tagOne);
        verify(mockedTagRepository).save(tagOne);
        tagService.addTag(tagTwo);
        verify(mockedTagRepository).save(tagTwo);
        tagService.addTag(tagThree);
        verify(mockedTagRepository).save(tagThree);
        tagService.addTag(tagFour);
        verify(mockedTagRepository).save(tagFour);
    }

    @Test
    public void shouldAddTagWithSpecialName() throws TagAlreadyExistsException {
        final Tag tag = new Tag("C++");
        tagService.addTag(tag);

        verify(mockedTagRepository).save(tag);
    }

    @Test(expected = TagAlreadyExistsException.class)
    public void shouldThrowTagExistsException() throws TagAlreadyExistsException {
        when(mockedTagRepository.existsById("Exists")).thenReturn(true);

        final Tag tag = new Tag("Exists");

        tagService.addTag(tag);
    }

    @Test
    public void shouldDeleteTagByIdSuccessfully() throws TagNotFoundException {
        when(mockedTagRepository.existsById("DeleteMe")).thenReturn(true);

        final Tag tag = new Tag("DeleteMe");

        tagService.deleteTag("DeleteMe");

        verify(mockedTagRepository).deleteById("DeleteMe");
    }

    @Test
    public void deleteTag1() {
    }

    @Test
    public void editTag() {
    }

    @Test
    public void doesTagExist() {
    }

    @Test
    public void doesTagExist1() {
    }

    @Test
    public void getTag() {
    }

    @Test
    public void getAllTags() {
    }
}