/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when a {@link com.gkenna.tullamoreqa.domain.Question} is invalid.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
public class QuestionInvalidException extends EntryNotFoundException {
    /**
     * Constructs a <tt>QuestionInvalidException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public QuestionInvalidException(final String message) {
        super(message);
    }
}
