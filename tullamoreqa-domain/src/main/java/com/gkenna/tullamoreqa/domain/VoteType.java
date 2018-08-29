/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

public enum VoteType {
    UPVOTE("Upvote", 1), DOWNVOTE("Downvote", -1);

    private final String voteType;
    private final int voteValue;

    VoteType(String voteType, int voteValue) {
        this.voteType = voteType;
        this.voteValue = voteValue;
    }

    public String getVoteType() {
        return voteType;
    }

    public int getVoteValue() {
        return voteValue;
    }
}
