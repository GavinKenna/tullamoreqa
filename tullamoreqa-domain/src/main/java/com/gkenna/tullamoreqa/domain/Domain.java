/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

/**
 * Comment Domain interface for all Domain classes.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
public interface Domain {
    /**
     * Patch the {@link Domain} with the given entity. Patch differs
     * from Update in that it checks what values are set in the update.
     * If the value is set (not null) then we use it.
     *
     * @param entity Updated values to use.
     * @param <T>    {@link Entry} subtype, be it Answer, Question, etc.
     * @since 0.0.11
     */
    <T extends Domain> void patch(T entity);
}
