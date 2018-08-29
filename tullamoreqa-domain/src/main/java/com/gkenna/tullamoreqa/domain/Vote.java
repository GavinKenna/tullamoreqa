/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "votes")
public class Vote implements Domain {

    @Id
    private Long id;

    @NotBlank
    private VoteType voteType;

    @Column(nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date voteCastDate;

    @ManyToOne
    @JoinColumn(name = "cast_by_user_username", nullable = false)
    private User voteCastBy;

    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = false)
    private Entry entry;

    public Vote(final User voteCastBy, final VoteType voteType) {
        this.setVoteCastBy(voteCastBy);
        this.setVoteType(voteType);
    }

    @Override
    public <T extends Domain> void patch(T entity) {

    }

    @Override
    public <T extends Domain> void update(T entity) {

    }

    public Long getId() {
        return id;
    }

    public Date getVoteCastDate() {
        return voteCastDate;
    }

    public void setVoteCastDate(Date voteCastDate) {
        this.voteCastDate = voteCastDate;
    }

    public User getVoteCastBy() {
        return voteCastBy;
    }

    public void setVoteCastBy(User voteCastBy) {
        this.voteCastBy = voteCastBy;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
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
    public int hashCode() {
        return Objects.hash(getId(), getVoteType(), getVoteCastDate(),
                getVoteCastBy(), entry);
    }

    @Override
    public String toString() {
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
