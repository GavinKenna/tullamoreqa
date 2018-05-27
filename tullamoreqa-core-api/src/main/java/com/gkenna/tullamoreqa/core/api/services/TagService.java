package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    void addTag(Tag tag);
    void deleteTag(Tag tag);
    void deleteTag(String id);
    void editTag(Tag tag);
    boolean doesTagExist(Tag tag);
    boolean doesTagExist(String id);
    Tag getTag(String id);
    Tag[] getAllTags();
}
