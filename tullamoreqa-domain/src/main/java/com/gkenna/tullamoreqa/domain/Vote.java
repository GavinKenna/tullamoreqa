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

@Entity
@Table(name = "votes")
public class Vote implements Domain {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @NotBlank
    private VoteType voteType;

    /**
     *
     */
    @Column(nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date voteCastDate;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "cast_by_user_username", nullable = false)
    private User voteCastBy;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = true)
    private Entry entry;

    /**
     *
     * @param voteCastBy
     * @param voteType
     */
    public Vote(final User voteCastBy, final VoteType voteType) {
        this.setVoteCastBy(voteCastBy);
        this.setVoteType(voteType);
        this.setVoteCastDate(Date.from(Instant.EPOCH));
    }

    /**
     *
     */
    protected Vote(){

    }

    @Override
    public final <T extends Domain> void patch(final T entity) {

    }

    @Override
    public final <T extends Domain> void update(final T entity) {

    }

    /**
     *
     * @return
     */
    public final Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public final Date getVoteCastDate() {
        return voteCastDate;
    }

    /**
     *
     * @param voteCastDate
     */
    public final void setVoteCastDate(Date voteCastDate) {
        this.voteCastDate = voteCastDate;
    }

    /**
     *
     * @return
     */
    public final User getVoteCastBy() {
        return voteCastBy;
    }

    /**
     *
     * @param voteCastBy
     */
    public void setVoteCastBy(final User voteCastBy) {
        this.voteCastBy = voteCastBy;
    }

    /**
     *
     * @return
     */
    public final VoteType getVoteType() {
        return voteType;
    }

    /**
     *
     * @param voteType
     */
    public void setVoteType(final VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public final boolean equals(Object o) {
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
