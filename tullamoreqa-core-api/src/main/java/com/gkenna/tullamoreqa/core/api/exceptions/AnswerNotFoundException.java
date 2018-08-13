/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when an {@link com.gkenna.tullamoreqa.domain.Answer} cannot be found.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public class AnswerNotFoundException extends EntryNotFoundException {
    /**
     * Constructs a <tt>AnswerNotFoundException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public AnswerNotFoundException(final String message) {
        super(message);
    }
}
