/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

/**
 * Domain interface for all Domain classes.
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
     * @param <T>   Domain subtype, be it Answer, Question, User etc.
     * @since 0.0.11
     */
    <T extends Domain> void patch(T entity);

    /**
     * Update the {@link Domain} with the given entity. Update differs
     * from Patch in that it doesn't check what values are set in the update.
     * If the value is null it isn't ignored and still used.
     *
     * @param entity Updated values to use.
     * @param <T>   Domain subtype, be it Answer, Question, User etc.
     * @since 0.0.11
     */
    <T extends Domain> void update(T entity);
}
