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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Question entity, which is written by a User.
 * Answers can be assigned to it, as well as Comments.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Entity
@Table(name = "questions")
public class Question extends Entry {

    /**
     * The last User to modify the Question,
     * be it updating the Body or anything else.
     */
    @ManyToOne
    @JoinColumn(name = "mod_user_username", nullable = true)
    private User modifiedBy = null;

    /**
     * List of Tags that help describe the Question, i.e. 'Java' related.
     */
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Tag> tags;

    /**
     * The exact Date and Time the Question was created at.
     */
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    /**
     * The exact Date and Time the Question was last updated
     * at.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdatedAt;

    /**
     * The Title of the Question, i.e. "How do I increment an integer in Java?"
     */
    @NotBlank
    private String title;

    /**
     * Question Constructor.
     */
    public Question() {
        //super();
        setTags(new HashSet<Tag>());
        setCreatedAt(new Date());
        setLastUpdatedAt(new Date());
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return getId() == question.getId()
                && getBody().equals(question.getBody())
                && getUpvotes() == question.getUpvotes()
                && getDownvotes() == question.getDownvotes()
                && getScore() == question.getScore()
                && Objects.equals(getModifiedBy(), question.getModifiedBy())
                && Objects.equals(getTags(), question.getTags())
                && Objects.equals(getCreatedAt(), question.getCreatedAt())
                && Objects.equals(getLastUpdatedAt(),
                question.getLastUpdatedAt())
                && Objects.equals(getTitle(), question.getTitle());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId(), getModifiedBy(), getTags(), getCreatedAt(),
                getLastUpdatedAt(), getTitle(),
                getUpvotes(), getDownvotes(), getScore());
    }

    /**
     * Return the Title of this Question.
     *
     * @return Title of this Question.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Set the Title of this Question.
     *
     * @param questionTitle Title of this Question.
     */
    public final void setTitle(final String questionTitle) {
        this.title = questionTitle;
    }

    /**
     * Return the User who last modified this Question.
     *
     * @return User who last modified this Question.
     */
    public final User getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Set the User who last modified this Question.
     *
     * @param modifiedBy User who last modified this Question.
     */
    public final void setModifiedBy(final User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Return the list of Tags that filter this Question.
     *
     * @return List of Tags that filter this Question.
     */
    public final Set<Tag> getTags() {
        return tags;
    }

    /**
     * Set the list of Tags that filter this Question.
     *
     * @param tags List of Tags that filter this Question.
     */
    public final void setTags(final Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Return the Date and Time that this Question was created.
     *
     * @return Date and Time that this Question was created.
     */
    public final Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the Date and Time that this Question was created.
     *
     * @param createdAt Date and Time that this Question was created.
     */
    public final void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Return the Date and Time that this Question was last modified.
     *
     * @return Date and Time that this Question was last modified.
     */
    public final Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    /**
     * Set the Date and Time that this Question was last modified.
     *
     * @param lastUpdatedAt Date and Time that this Question was last modified.
     */
    public final void setLastUpdatedAt(final Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    /**
     * Add a Tag to the list of Tags within this Question.
     *
     * @param tag Tag to add.
     * @since 0.0.11
     */
    public final void addTag(final Tag tag) {
        if (!tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    /**
     * Remove a Tag to the list of Tags within this Question.
     *
     * @param tag Tag to remove.
     * @since 0.0.11
     */
    public final void remoteTag(final Tag tag) {
        if (!tags.contains(tag)) {
            this.tags.remove(tag);
        }
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("Question{");
        sb.append("modifiedBy=").append(modifiedBy);
        sb.append(", id=").append(getId());
        sb.append(", tags=").append(tags);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", lastUpdatedAt=").append(lastUpdatedAt);
        sb.append(", title='").append(title).append('\'');
        sb.append(", createdBy=").append(getCreatedAt());
        sb.append(", body='").append(getBody()).append('\'');
        sb.append(", upvotes=").append(getUpvotes());
        sb.append(", downvotes=").append(getDownvotes());
        sb.append('}');
        return sb.toString();
    }
}
