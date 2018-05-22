package com.gkenna.tullamoreqa.domain.service.api;

import com.gkenna.tullamoreqa.domain.Tag;

public interface TagService {
    void addTag(Tag Tag);
    void deleteTag(Tag Tag);
    void deleteTag(String id);
    void editTag(Tag Tag);
    boolean doesTagExist(Tag Tag);
    boolean doesTagExist(String id);
    Tag getTag(String id);
    Tag[] getAllTags();
}
