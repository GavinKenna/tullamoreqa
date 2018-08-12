/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.stereotype.Service;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Tag}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.TagRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface TagService {
    void addTag(final Tag tag);

    void deleteTag(final Tag tag);

    Tag deleteTag(final String id);

    Tag updateTag(final String tagId, final Tag input);

    boolean doesTagExist(final Tag tag);

    boolean doesTagExist(final String id);

    Tag getTag(final String id);

    Tag[] getAllTags();

}
