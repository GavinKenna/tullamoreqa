/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when a {@link com.gkenna.tullamoreqa.domain.Tag} already exists.
 *
 * @author Gavin Kenna
 * @since 0.0.10
 */
public class TagAlreadyExistsException extends Throwable {
    /**
     * Constructs a <tt>TagAlreadyExistsException</tt> with the
     * specified cause.
     *
     * @param message Additional exception information.
     */
    public TagAlreadyExistsException(final String message) {
        super(message);
    }
}
