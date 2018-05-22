package com.gkenna.tullamoreqa.domain.service;

import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.repositories.CommentRepository;
import com.gkenna.tullamoreqa.domain.repositories.TagRepository;
import com.gkenna.tullamoreqa.domain.service.api.TagService;
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
