/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * An Authority entity, which describes what a User can and can't do.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
@Entity
@Table(name = "authorities")
public class Authority implements Domain {

    /**
     * The name of the Authority, seen as the ID.
     */
    @Id
    @NotBlank
    @NotNull
    private String authorityName;

    @NotBlank
    @NotNull
    private String description;

    /**
     * Create a new Authority.
     *
     * @param authorityName The unique ID of the Authority.
     * @since 0.0.11
     */
    public Authority(final String authorityName) {
        this.authorityName = authorityName;
    }

    public final <T extends Domain> void patch(final T entity) {

    }

    @Override
    public final <T extends Domain> void update(final T entity) {

    }

    public final String getAuthorityName() {
        return authorityName;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }
}
