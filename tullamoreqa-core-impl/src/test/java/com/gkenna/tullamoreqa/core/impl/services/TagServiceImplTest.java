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
    public void shouldDeleteTagSuccessfully() throws TagNotFoundException {
        when(mockedTagRepository.existsById("DeleteMe")).thenReturn(true);

        final Tag tag = new Tag("DeleteMe");

        tagService.deleteTag(tag);

        verify(mockedTagRepository).deleteById("DeleteMe");
    }

    @Test(expected = TagNotFoundException.class)
    public void shouldThrowExceptionWhenDeletingTagIdThatDoesNotExist() throws TagNotFoundException {
        when(mockedTagRepository.existsById("DeleteMe")).thenReturn(false);

        final Tag tag = new Tag("DeleteMe");

        tagService.deleteTag("DeleteMe");

        verify(mockedTagRepository).deleteById("DeleteMe");
    }

    @Test(expected = TagNotFoundException.class)
    public void shouldThrowExceptionWhenDeletingTagThatDoesNotExist() throws TagNotFoundException {
        when(mockedTagRepository.existsById("DeleteMe")).thenReturn(false);

        final Tag tag = new Tag("DeleteMe");

        tagService.deleteTag(tag);

        verify(mockedTagRepository).deleteById("DeleteMe");
    }

    @Test
    public void shouldUpdateValidTagSuccessfully() throws TagNotFoundException {
        when(mockedTagRepository.existsById("OriginalTag")).thenReturn(true);

        final Tag originalTag = new Tag("OriginalTag");
        originalTag.setDescription("Original Description");

        when(mockedTagRepository.getOne("OriginalTag")).thenReturn(originalTag);

        final Tag input = new Tag("OriginalTag");
        input.setDescription("New Description");

        final Tag updated = tagService.updateTag("OriginalTag", input);

        verify(mockedTagRepository).existsById("OriginalTag");
        verify(mockedTagRepository).save(originalTag);

        assert (updated.equals(input));
        assert (updated.getDescription().equals(input.getDescription()));
    }

    @Test(expected = TagNotFoundException.class)
    public void shouldThrowExceptionWhenUpdating() throws TagNotFoundException {
        when(mockedTagRepository.existsById("OriginalTag")).thenReturn(false);

        final Tag originalTag = new Tag("OriginalTag");
        originalTag.setDescription("Original Description");

        final Tag input = new Tag("OriginalTag");
        input.setDescription("New Description");

        final Tag updated = tagService.updateTag("OriginalTag", input);

        verify(mockedTagRepository).existsById("OriginalTag");
        verify(mockedTagRepository).save(originalTag);
    }

    @Test
    public void shouldGetTagSuccessfully() throws TagNotFoundException {
        when(mockedTagRepository.existsById("GetMe")).thenReturn(true);

        final Tag tag = new Tag("GetMe");

        when(mockedTagRepository.getOne("GetMe")).thenReturn(tag);

        assert (tagService.getTag("GetMe").equals(tag));

        verify(mockedTagRepository).getOne("GetMe");
    }

    @Test(expected = TagNotFoundException.class)
    public void shouldThrowExceptionWhenGettingTag() throws TagNotFoundException {
        when(mockedTagRepository.existsById("GetMe")).thenReturn(false);

        tagService.getTag("GetMe");
    }
}