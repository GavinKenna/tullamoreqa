/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment extends Entry {

    @ManyToOne(targetEntity = Entry.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Entry parent;

    private Comment() {
    }


    @Override
    public String toString() {
        return "Comment{" +
                "parent=" + parent +
                ", user=" + user +
                ", body='" + body + '\'' +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(parent, comment.parent) &&
                Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(parent, getId());
    }
}
