/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when a {@link com.gkenna.tullamoreqa.domain.Question}
 * cannot be found.
 *
 * @author Gavin Kenna
 * @since 0.0.8
 */
public class QuestionNotFoundException extends EntryNotFoundException {
    /**
     * Constructs a <tt>QuestionNotFoundException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public QuestionNotFoundException(final String message) {
        super(message);
    }
}
