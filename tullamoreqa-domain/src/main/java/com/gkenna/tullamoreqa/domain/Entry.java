/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entry {
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_username")
    protected User user;

    @NotBlank
    protected String body;
    protected int upvotes;
    protected int downvotes;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Entry(User user, @NotBlank String body) {
        this.user = user;
        this.body = body;
    }

    protected Entry() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public int getScore() {
        return this.getUpvotes() - this.getDownvotes();
    }

    @Override
    public String toString() {
        return "Entry{" +
                "user=" + user +
                ", body='" + body + '\'' +
                ", id=" + id +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                '}';
    }
}
