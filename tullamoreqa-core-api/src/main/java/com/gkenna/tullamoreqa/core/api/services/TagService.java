/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    void addTag(Tag tag);
    void deleteTag(Tag tag);
    Tag deleteTag(String id);
    Tag updateTag(String tagId, Tag input);
    boolean doesTagExist(Tag tag);
    boolean doesTagExist(String id);
    Tag getTag(String id);
    Tag[] getAllTags();

}
