/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when a generic Entry cannot be found.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
public abstract class EntryNotFoundException extends Throwable {
    /**
     * Constructs a <tt>AnswerNotFoundException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public EntryNotFoundException(final String message) {
    }
}
