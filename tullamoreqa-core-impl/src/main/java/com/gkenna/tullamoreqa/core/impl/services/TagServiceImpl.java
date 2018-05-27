package com.gkenna.tullamoreqa.core.impl.services;

import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    @Override
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Tag tag) {

    }

    @Override
    public void deleteTag(String id) {

    }

    @Override
    public void editTag(Tag tag) {

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
