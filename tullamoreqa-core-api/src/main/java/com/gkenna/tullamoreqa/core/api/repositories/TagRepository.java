/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repository for containing {@link Tag}s. Will be called by the responsible
 * Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.TagService}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    /**
     * Filter all {@link Tag}s by textual description.
     *
     * @param description Description to filter Tags by.
     * @return Collection of relevant Tags.
     */
    Collection<Tag> findByDescription(String description);
}