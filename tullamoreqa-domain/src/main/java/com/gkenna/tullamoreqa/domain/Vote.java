/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * Vote Domain. Contains information about what {@link Entry} is being Voted on,
 * what {@link User} cast the Vote and what
 * {@link VoteType} it is (Upvote or Downvote).
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
@Entity
@Table(name = "votes")
public class Vote implements Domain {

    /**
     * ID of the Vote.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The {@link VoteType} of this Vote, i.e. be it Upvote or Downvote.
     */
    @NotBlank
    private VoteType voteType;

    /**
     * The {@link Date} in which this Vote is being cast.
     */
    @Column(nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date voteCastDate;

    /**
     * The {@link User} who is casting this Vote.
     */
    @ManyToOne
    @JoinColumn(name = "cast_by_user_username", nullable = false)
    private User voteCastBy;

    /**
     * The {@link Entry} that is being Voted on.
     */
    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = true)
    private Entry entry;

    /**
     * Construct a Vote object.
     *
     * @param voteCastBy The {@link User} who is casting the Vote.
     * @param voteType   The {@link VoteType} of this Vote.
     */
    public Vote(final User voteCastBy, final VoteType voteType) {
        this.setVoteCastBy(voteCastBy);
        this.setVoteType(voteType);
        this.setVoteCastDate(Date.from(Instant.EPOCH));
    }

    /**
     * Default constructor of Vote.
     */
    protected Vote() {

    }

    @Override
    public final <T extends Domain> void patch(final T entity) {

    }

    @Override
    public final <T extends Domain> void update(final T entity) {

    }

    /**
     * Return the ID of this Vote.
     *
     * @return The ID of this Vote.
     */
    public final Long getId() {
        return id;
    }

    /**
     * Get the {@link Date} that this Vote was cast.
     *
     * @return The {@link Date} that this Vote was cast.
     */
    public final Date getVoteCastDate() {
        return voteCastDate;
    }

    /**
     * Set the {@link Date} that this Vote was cast.
     *
     * @param voteCastDate The {@link Date} this Vote was cast.
     */
    public final void setVoteCastDate(final Date voteCastDate) {
        this.voteCastDate = voteCastDate;
    }

    /**
     * Get the {@link User} who cast this Vote.
     *
     * @return The {@link User} who cast this Vote.
     */
    public final User getVoteCastBy() {
        return voteCastBy;
    }

    /**
     * Set the {@link User} who cast the Vote.
     *
     * @param voteCastBy The {@link User} who cast the Vote.
     */
    public final void setVoteCastBy(final User voteCastBy) {
        this.voteCastBy = voteCastBy;
    }

    /**
     * Get what type of Vote this is, be it Upvote or Downvote.
     *
     * @return Upvote or Downvote.
     */
    public final VoteType getVoteType() {
        return voteType;
    }

    /**
     * Set this Votes {@link VoteType}, be it an Upvote or a Downvote.
     *
     * @param voteType Upvote or Downvote.
     */
    public final void setVoteType(final VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vote)) {
            return false;
        }
        Vote vote = (Vote) o;
        return Objects.equals(getId(), vote.getId())
                && getVoteType() == vote.getVoteType()
                && Objects.equals(getVoteCastDate(), vote.getVoteCastDate())
                && Objects.equals(getVoteCastBy(), vote.getVoteCastBy())
                && Objects.equals(entry, vote.entry);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId(), getVoteType(), getVoteCastDate(),
                getVoteCastBy(), entry);
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("Vote{");
        sb.append("id=").append(id);
        sb.append(", voteType=").append(voteType);
        sb.append(", voteCastDate=").append(voteCastDate);
        sb.append(", voteCastBy=").append(voteCastBy);
        sb.append(", entry=").append(entry);
        sb.append('}');
        return sb.toString();
    }
}
