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

    private int upvotes;
    private int downvotes;

    private Comment() {
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + this.getId() +
                ", parent=" + parent +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", user=" + user +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getId() == comment.getId() &&
                getUpvotes() == comment.getUpvotes() &&
                getDownvotes() == comment.getDownvotes() &&
                Objects.equals(parent, comment.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), parent, getUpvotes(), getDownvotes());
    }
}
