/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service("tagService")
//@EnableTransactionManagement
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LogManager.getLogger(TagServiceImpl.class);


    @Autowired
    private TagRepository tagRepository;


    @Override
    //@Transactional
    public void addTag(Tag tag) {
        LOGGER.debug("Adding {}", tag);
        tagRepository.save(tag);
        tagRepository.flush();
    }

    @Override
    public void deleteTag(Tag tag) {

    }

    @Override
    public Tag deleteTag(String id) {

        return null;
    }

    @Override
    public Tag updateTag(String tagId, Tag input) {
        return null;
    }

    @Override
    public boolean doesTagExist(Tag tag) {
        return false;
    }

    @Override
    public boolean doesTagExist(String id) {
        return tagRepository.existsById(id);
    }

    @Override
    public Tag getTag(String id) {
        return null;
    }

    @Override
    public Tag[] getAllTags() {
        return new Tag[0];
    }
}
