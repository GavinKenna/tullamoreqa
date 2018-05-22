package com.gkenna.tullamoreqa.domain.service;

import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.service.api.TagService;

public class TagServiceImpl implements TagService {
    @Override
    public void addTag(Tag Tag) {

    }

    @Override
    public void deleteTag(Tag Tag) {

    }

    @Override
    public void deleteTag(String id) {

    }

    @Override
    public void editTag(Tag Tag) {

    }

    @Override
    public boolean doesTagExist(Tag Tag) {
        return false;
    }

    @Override
    public boolean doesTagExist(String id) {
        return false;
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
