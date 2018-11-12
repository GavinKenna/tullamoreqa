/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

/**
 * VoteType Enum. Specifies the type of Vote, be it Upvote or Downvote
 * and the {@link Vote}s numerical weight.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
public enum VoteType {

    /**
     * An Upvote. This carries +1 to the total score of the {@link Entry}.
     */
    UPVOTE("Upvote", 1),

    /**
     * An Upvote. This carries -1 to the total score of the {@link Entry}.
     */
    DOWNVOTE("Downvote", -1);

    /**
     * Textual representation of the Vote Type.
     * By default there will be two - Upvote and Downvote.
     */
    private final String voteType;

    /**
     * Numerical value of the Vote. Upvote will add +1 to the Score,
     * while a Downvote will add -1 to the score.
     */
    private final int voteValue;

    /**
     * Default constructor for VoteType enum.
     *
     * @param voteType  String representation of the Vote Type.
     * @param voteValue Numerical value of the Vote.
     */
    VoteType(final String voteType, final int voteValue) {
        this.voteType = voteType;
        this.voteValue = voteValue;
    }

    /**
     * The String representation of the {@link Vote}.
     *
     * @return UPVOTE or DOWNVOTE.
     */
    public final String getVoteType() {
        return voteType;
    }

    /**
     * Return the numerical value of the {@link Vote}.
     *
     * @return 1 for Upvote and -1 for Downvote.
     */
    public final int getVoteValue() {
        return voteValue;
    }
}
