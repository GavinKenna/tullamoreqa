/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it.services;

import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.it.AppConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
public class TagServiceIT {
    private static final Logger LOGGER = LogManager.getLogger(TagServiceIT.class);

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Test
    @Transactional
    public void shouldAddValidTagSuccessfully() throws TagAlreadyExistsException {
        final Tag tag = new Tag("Numberwang");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        tagService.addTag(tag);

        /*
        Assert that Numberwang Tag now exists.
         */
        assert tagRepository.existsById("Numberwang");

        /*
        Delete Numberwang
         */
        tagRepository.deleteById("Numberwang");

        /*
        Assert that Numberwang Tag doesn't exist.
         */
        assert !tagRepository.existsById("Numberwang");
    }

    @Test
    @Transactional
    public void shouldAddValidTagWithDescriptionSuccessfully() throws TagAlreadyExistsException {
        final Tag tag = new Tag("Numberwang");
        tag.setDescription("Numberwang description.");

        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        tagService.addTag(tag);

        /*
        Assert that Numberwang Tag now exists.
         */
        assert tagRepository.existsById("Numberwang");

        /*
        Delete Numberwang
         */
        tagRepository.deleteById("Numberwang");

        /*
        Assert that Numberwang Tag doesn't exist.
         */
        assert !tagRepository.existsById("Numberwang");
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

        try {
            tagService.addTag(tag);
            /*
            If we didn't get the exception then
            return error
             */
            tagRepository.deleteById("Numberwang");
            assert false;
        } catch (TagAlreadyExistsException e) {
            assert e.getClass().equals(TagAlreadyExistsException.class);
        }

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

        final Tag entity = tagService.getTag("Numberwang");

        assert (entity.getName().equals("Numberwang"));
        assert (entity.getDescription().equals("The Tag for " +
                "Numberwang Questions."));

        /*
        Cleanup the DB for future tests.
         */
        tagRepository.deleteById("Numberwang");

        tagRepository.flush();
    }

    @Test(expected = TagNotFoundException.class)
    public void shouldGetTagNotFoundExceptionWhenTryingToGetInvalidTag() throws Exception,
            TagAlreadyExistsException, TagNotFoundException {
        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        tagService.getTag("Numberwang");

    }

    @Test
    public void shouldReturnTrueForTagExistsUsingId() {
        /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        final Tag tag = new Tag("Numberwang");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Add Numberwang
         */
        tagRepository.saveAndFlush(tag);

        assert tagRepository.existsById("Numberwang");

        final boolean doesTagExist = tagService.doesTagExist("Numberwang");

        assert doesTagExist;

        tagRepository.deleteById("Numberwang");
    }

    @Test
    public void shouldReturnTrueForTagExists() {
       /*
        Assert that Numberwang Tag doesn't exist yet.
         */
        assert !tagRepository.existsById("Numberwang");

        final Tag tag = new Tag("Numberwang");
        tag.setDescription("The Tag for Numberwang Questions.");

        /*
        Add Numberwang
         */
        tagRepository.saveAndFlush(tag);

        assert tagRepository.existsById("Numberwang");

        final boolean doesTagExist = tagService.doesTagExist(tag);

        assert doesTagExist;

        tagRepository.deleteById("Numberwang");
    }

    @Test
    public void shouldReturnFalseForTagExistsUsingId() {
        final boolean doesTagExist = tagService.doesTagExist("NotExists");

        assert !doesTagExist;
    }

    @Test
    public void shouldReturnFalseForTagExists() {
        final Tag tag = new Tag("NotExists");

        final boolean doesTagExist = tagService.doesTagExist(tag);

        assert !doesTagExist;
    }
}
