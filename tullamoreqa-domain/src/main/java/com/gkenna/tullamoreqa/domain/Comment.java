package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment extends Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Entry.class)
    @JoinColumn(name = "entry_id")
    private Entry parent;

    private int upvotes;
    private int downvotes;

    public Comment() {
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

    public long getId() {
        return id;
    }
}
