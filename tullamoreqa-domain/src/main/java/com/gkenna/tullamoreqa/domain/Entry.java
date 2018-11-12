/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * An Abstract class that allows for Users to create
 * text based objects, i.e. Answers, Questions and Comments.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entry implements Domain {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Vote> votes;
    /**
     * The User who created the Entry.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "createdBy_username")
    private User createdBy;
    /**
     * The last User to modify the Entry,
     * be it updating the Body or anything else.
     */
    @ManyToOne
    @JoinColumn(name = "mod_user_username", nullable = true)
    private User modifiedBy = null;
    /**
     * The exact Date and Time the Entry was created at.
     */
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    /**
     * The exact Date and Time the Entry was last updated
     * at.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdatedAt;
    /**
     * The Body of the Entry. Contains the bulk of the textual
     * information.
     * If this was a Question it would contain the Question.
     */
    @NotBlank
    private String body;
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
        this.createdAt = new Date();
        this.lastUpdatedAt = new Date();
        this.votes = new HashSet<>();
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
    public final Integer getUpvotes() {
        int upvotes = 0;
        for (Vote v : this.getVotes()) {
            if (v.getVoteType() == VoteType.UPVOTE) {
                upvotes++;
            }
        }
        return upvotes;
    }

    /**
     * Return the Downvotes of the Entry.
     *
     * @return Downvotes of the Entry.
     */
    public final Integer getDownvotes() {
        int downvotes = 0;
        for (Vote v : this.votes) {
            if (v.getVoteType() == VoteType.DOWNVOTE) {
                downvotes++;
            }
        }
        return downvotes;
    }

    /**
     * Return the Score of this Entry, which is Upvotes - Downvotes.
     *
     * @return Upvotes - Downvotes.
     */
    public final Integer getScore() {
        int score = 0;
        for (Vote v : this.votes) {
           score += v.getVoteType().getVoteValue();
        }
        return score;
    }

    /**
     * Return the User who last modified this Entry.
     *
     * @return User who last modified this Entry.
     */
    public final User getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Set the User who last modified this Entry.
     *
     * @param modifiedBy User who last modified this Entry.
     */
    public final void setModifiedBy(final User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Return the Date and Time that this Entry was created.
     *
     * @return Date and Time that this Entry was created.
     */
    public final Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the Date and Time that this Entry was created.
     *
     * @param createdAt Date and Time that this Entry was created.
     */
    public final void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Return the Date and Time that this Entry was last modified.
     *
     * @return Date and Time that this Entry was last modified.
     */
    public final Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    /**
     * Set the Date and Time that this Entry was last modified.
     *
     * @param lastUpdatedAt Date and Time that this Entry was last modified.
     */
    public final void setLastUpdatedAt(final Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(final Object o);

    @Override
    public abstract int hashCode();

    /**
     *
     * @param entry
     * @param <T>
     */
    public <T extends Domain> void patch(final T entry) {
        final Entry input = (Entry) entry;
        final String entryBody = input.getBody();
        final User entryCreatedBy = input.getCreatedBy();
        final User entryModifiedBy = input.getModifiedBy();
        final Integer entryUpvotes = input.getUpvotes();
        final Integer entryDownvotes = input.getDownvotes();

        if (entryBody != null) {
            this.setBody(entryBody);
        }
        if (entryCreatedBy != null) {
            this.setCreatedBy(entryCreatedBy);
        }
        if (entryModifiedBy != null) {
            this.setModifiedBy(entryModifiedBy);
        }
    }

    /**
     *
     * @param entry
     * @param <T>
     */
    public <T extends Domain> void update(final T entry) {
        final Entry input = (Entry) entry;
        final String entryBody = input.getBody();
        final User entryCreatedBy = input.getCreatedBy();
        final User entryModifiedBy = input.getModifiedBy();
        final Integer entryUpvotes = input.getUpvotes();
        final Integer entryDownvotes = input.getDownvotes();

        this.setBody(entryBody);
        this.setCreatedBy(entryCreatedBy);
        this.setModifiedBy(entryModifiedBy);
    }

    /**
     *
     * @param votes
     */
    public void setVotes(final Set<Vote> votes) {
        this.votes = votes;
    }

    /**
     *
     * @return
     */
    public final Set<Vote> getVotes() {
        return this.votes;
    }

    /**
     *
     * @param vote
     */
    public void addVote(final Vote vote) {
        this.votes.add(vote);
    }
}
