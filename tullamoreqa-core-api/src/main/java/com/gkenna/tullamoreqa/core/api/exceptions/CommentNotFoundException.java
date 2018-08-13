/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when an {@link com.gkenna.tullamoreqa.domain.Comment}
 * cannot be found.
 *
 * @author Gavin Kenna
 * @since 0.0.8
 */
public class CommentNotFoundException extends EntryNotFoundException {
    /**
     * Constructs a <tt>CommentNotFoundException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public CommentNotFoundException(final String message) {
        super(message);
    }
}
