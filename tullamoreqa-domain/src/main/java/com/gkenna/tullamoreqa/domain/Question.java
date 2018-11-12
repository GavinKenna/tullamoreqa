/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
     * List of Tags that help describe the Question, i.e. 'Java' related.
     */
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Tag> tags;

    /**
     * The Title of the Question, i.e. "How do I increment an integer in Java?"
     */
    @NotBlank
    private String title;

    /**
     * Question Constructor.
     */
    public Question() {
        super();
        this.tags = new HashSet<Tag>();
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

    @SuppressWarnings("checkstyle:HiddenField")
    @Override
    public final <T extends Domain> void patch(final T entry) {
        super.patch(entry);
        final Question input = (Question) entry;
        final String title = input.getTitle();
        final Set<Tag> tags = input.tags;
        final Set<Vote> votes = input.getVotes();

        if (title != null) {
            this.setTitle(title);
        }
        if (tags != null) {
            this.setTags(tags);
        }
        if (votes != null) {
            this.setVotes(votes);
        }
    }

    @SuppressWarnings("checkstyle:HiddenField")
    @Override
    public final <T extends Domain> void update(final T entry) {
        super.patch(entry);
        final Question input = (Question) entry;
        final String title = input.getTitle();
        final Set<Tag> tags = input.getTags();
        final Set<Vote> votes = input.getVotes();

        this.setTitle(title);
        this.setTags(tags);
        this.setVotes(votes);
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
        sb.append("modifiedBy=").append(getModifiedBy());
        sb.append(", id=").append(getId());
        sb.append(", tags=").append(tags);
        sb.append(", createdAt=").append(getCreatedAt());
        sb.append(", lastUpdatedAt=").append(getLastUpdatedAt());
        sb.append(", title='").append(title).append('\'');
        sb.append(", createdBy=").append(getCreatedBy());
        sb.append(", body='").append(getBody()).append('\'');
        sb.append(", upvotes=").append(getUpvotes());
        sb.append(", downvotes=").append(getDownvotes());
        sb.append('}');
        return sb.toString();
    }
}
