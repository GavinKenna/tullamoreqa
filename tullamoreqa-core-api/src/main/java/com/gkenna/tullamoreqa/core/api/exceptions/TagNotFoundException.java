/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when an {@link com.gkenna.tullamoreqa.domain.Tag} cannot be found.
 *
 * @author Gavin Kenna
 * @since 0.0.8
 */
public class TagNotFoundException extends EntryNotFoundException {
    /**
     * Constructs a <tt>TagNotFoundException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public TagNotFoundException(final String message) {
        super(message);
    }
}
