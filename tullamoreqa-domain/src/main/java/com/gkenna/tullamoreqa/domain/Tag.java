/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * A Tag is an entity that can be associated with a Question. A Question can
 * have multiple Tags that act as labels to describe a Question, i.e.
 * 'java-question', 'c++-question', 'unix-question'.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Entity
@Table(name = "tags")
public class Tag implements Domain {

    /**
     * The Label of the Tag. Acts as the ID of the tag.
     */
    @Id
    private String name;

    /**
     * Description of the Tag.
     */
    @NotBlank
    private String description;

    /**
     * Constructor of Tag. The name parameter will act as the ID.
     *
     * @param name ID of the Tag.
     */
    public Tag(final String name) {
        setDescription("Desc");
        this.name = name;
    }

    /**
     * Default Constructor. Shouldn't be called.
     * See Effective Java (2nd+3rd Edition).
     */
    public Tag() {

    }

    /**
     * Get the name of the Tag.
     *
     * @return Name of Tag.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the ID.
     *
     * @return The Tag name.
     */
    public final String getId() {
        return this.getName();
    }

    /**
     * Returns the Description of the Tag.
     *
     * @return Description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set the Description of the Tag.
     *
     * @param description The description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        final Tag tag = (Tag) o;
        return Objects.equals(getName(), tag.getName())
                && Objects.equals(getDescription(), tag.getDescription());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    @SuppressWarnings("checkstyle:HiddenField")
    public final <T extends Domain> void patch(final T entity) {
        final Tag input = (Tag) entity;
        final String description = input.getDescription();

        if (description != null) {
            this.setDescription(description);
        }
    }

    @Override
    public final <T extends Domain> void update(final T entity) {
        final Tag input = (Tag) entity;
        this.setDescription(input.getDescription());
    }
}
