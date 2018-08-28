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

@Entity
@Table(name = "votes")
public class Vote implements Domain {

    @Id
    private Long id;

    @NotBlank
    private boolean isUpvote;

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

    public Vote(final User voteCastBy, final boolean isUpvote) {
        this.setVoteCastBy(voteCastBy);
        this.setUpvote(isUpvote);
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

    public boolean isUpvote() {
        return isUpvote;
    }

    public void setUpvote(boolean upvote) {
        isUpvote = upvote;
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
}
