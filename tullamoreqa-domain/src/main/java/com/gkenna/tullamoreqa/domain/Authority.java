/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
     * The name of the #{@link Authority}, seen as the ID.
     */
    @Id
    @NotBlank
    @NotNull
    private String authorityName;

    /**
     * Description of the #{@link Authority}. Describes what this
     * #{@link Authority} can do , i.e.
     * "Can Delete Comments, Cannot Delete Questions, etc."
     */
    @NotBlank
    @NotNull
    private String description;

    /**
     * Create a new #{@link Authority}.
     *
     * @param authorityName The unique ID of the #{@link Authority}.
     * @since 0.0.11
     */
    public Authority(final String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public final <T extends Domain> void patch(final T entity) {
        final Authority input = (Authority) entity;
        final String inputDescription = input.getDescription();
        if (inputDescription != null) {
            this.setDescription(inputDescription);
        }
    }

    @Override
    public final <T extends Domain> void update(final T entity) {
        final Authority input = (Authority) entity;
        final String inputDescription = input.getDescription();

        this.setDescription(inputDescription);
    }

    /**
     * Return the name of the #{@link Authority}.
     *
     * @return Name of the #{@link Authority}, acts as the ID of the Authority.
     * @since 0.0.11
     */
    public final String getAuthorityName() {
        return authorityName;
    }

    /**
     * Return the description of the #{@link Authority} - what the Authority
     * will allow the #{@link User} to do.
     *
     * @return Description of the #{@link Authority}.
     * @since 0.0.11
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set the description of the #{@link Authority} - what the Authority
     * will allow the #{@link User} to do.
     *
     * @param description Description of the #{@link Authority}.
     * @since 0.0.11
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        Authority authority = (Authority) o;
        return Objects.equals(getAuthorityName(), authority.getAuthorityName())
                && Objects.equals(getDescription(), authority.getDescription());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getAuthorityName(), getDescription());
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("Authority{");
        sb.append("authorityName='").append(authorityName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
