/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for containing {@link Entry}s. Will be called by the responsible
 * Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.EntryService}.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
