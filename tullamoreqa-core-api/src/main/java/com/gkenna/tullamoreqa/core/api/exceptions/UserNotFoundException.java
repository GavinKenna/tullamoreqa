/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.exceptions;

/**
 * Thrown when an {@link com.gkenna.tullamoreqa.domain.User} cannot be found.
 *
 * @author Gavin Kenna
 * @since 0.0.8
 */
public class UserNotFoundException extends EntryNotFoundException {
    /**
     * Constructs a <tt>UserNotFoundException</tt> with the specified cause.
     *
     * @param message Additional exception information.
     */
    public UserNotFoundException(final String message) {
        super(message);
    }
}
