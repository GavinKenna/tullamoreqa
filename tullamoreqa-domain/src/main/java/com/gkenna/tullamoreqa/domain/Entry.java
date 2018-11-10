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

/**
 * An Abstract class that allows for Users to create
 * text based objects, i.e. Answers, Questions and Comments.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entry {

    /**
     * The User who created the Entry.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "createdBy_username")
    private User createdBy;

    /**
     * The Body of the Entry. Contains the bulk of the textual
     * information.
     * If this was a Question it would contain the Question.
     */
    @NotBlank
    private String body;

    /**
     * How many Upvotes from other Users does this Entry have.
     */
    private int upvotes;

    /**
     * How many Downvotes from other Users does this Entry have.
     */
    private int downvotes;

    /**
     * The ID of the Entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Create a new Entry.
     *
     * @param createdBy The User creating this Entry.
     * @param body      The Body the User is populating.
     */
    public Entry(final User createdBy, final @NotBlank String body) {
        this();
        setCreatedBy(createdBy);
        setBody(body);
    }

    /**
     * Default Constructor. Shouldn't be called.
     * See Effective Java (2nd+3rd Edition).
     */
    protected Entry() {
    }

    /**
     * Return the ID of the Entry.
     *
     * @return ID of the Entry.
     */
    public final Long getId() {
        return id;
    }

    /**
     * Return the User who created this Entry.
     *
     * @return User who created this Entry.
     */
    public final User getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the User who created this Entry.
     *
     * @param createdBy User who created this Entry.
     */
    public final void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Return the Body of the Entry.
     *
     * @return Body of the Entry.
     */
    public final String getBody() {
        return body;
    }

    /**
     * Set the Body of this Entry.
     *
     * @param body Body of this Entry.
     */
    public final void setBody(final String body) {
        this.body = body;
    }

    /**
     * Return the Upvotes of the Entry.
     *
     * @return Upvotes of the Entry.
     */
    public final int getUpvotes() {
        return upvotes;
    }

    /**
     * Set the Upvotes of this Entry.
     *
     * @param upvotes Upvotes of this Entry.
     */
    public final void setUpvotes(final int upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * Return the Downvotes of the Entry.
     *
     * @return Downvotes of the Entry.
     */
    public final int getDownvotes() {
        return downvotes;
    }

    /**
     * Set the Downvotes of this Entry.
     *
     * @param downvotes Downvotes of this Entry.
     */
    public final void setDownvotes(final int downvotes) {
        this.downvotes = downvotes;
    }

    /**
     * Return the Score of this Entry, which is Upvotes - Downvotes.
     *
     * @return Upvotes - Downvotes.
     */
    public final int getScore() {
        return this.getUpvotes() - this.getDownvotes();
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(final Object o);

    @Override
    public abstract int hashCode();
}
