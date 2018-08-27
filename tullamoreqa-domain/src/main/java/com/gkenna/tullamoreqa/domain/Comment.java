/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

/**
 * A Comment entity, which is written by a User in
 * response to an Answer, Question or another
 * Comment.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Entity
@Table(name = "comments")
public final class Comment extends Entry {

    /**
     * The parent of this object.
     * Every Comment is in response to an Entry, be it
     * an Answer, Question or another Comment.
     */
    @ManyToOne(targetEntity = Entry.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Entry parent;

    /**
     * Default Constructor. Shouldn't be called.
     * See Effective Java (2nd+3rd Edition).
     */
    private Comment() {
    }

    @Override
    public final <T extends Domain> void patch(final T entry) {
        super.patch(entry);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(parent, comment.parent)
                && Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, getId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("parent=").append(parent);
        sb.append(", createdBy=").append(getCreatedBy());
        sb.append(", body='").append(getBody()).append('\'');
        sb.append(", upvotes=").append(getUpvotes());
        sb.append(", downvotes=").append(getDownvotes());
        sb.append('}');
        return sb.toString();
    }
}
