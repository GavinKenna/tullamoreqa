/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.services;

import com.gkenna.tullamoreqa.core.api.exceptions.TagAlreadyExistsException;
import com.gkenna.tullamoreqa.core.api.exceptions.TagNotFoundException;
import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.stereotype.Service;

/**
 * API for interacting with {@link com.gkenna.tullamoreqa.domain.Tag}.
 * This API communicates directly with the
 * {@link com.gkenna.tullamoreqa.core.api.repositories.TagRepository}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Service
public interface TagService {
    /**
     * Insert a new {@link Tag} to the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.TagRepository}.
     *
     * @param tag {@link Tag} to Add.
     * @throws TagAlreadyExistsException Thrown when a Tag with the supplied
     *                                   ID already exists.
     */
    void addTag(final Tag tag) throws TagAlreadyExistsException;

    /**
     * Delete a {@link Tag} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.TagRepository}.
     *
     * @param tag {@link Tag} to Delete.
     * @throws TagNotFoundException Thrown if {@link Tag}
     *                              cannot be found.
     */
    void deleteTag(final Tag tag) throws TagNotFoundException;

    /**
     * Delete a {@link Tag} from the
     * {@link com.gkenna.tullamoreqa.core.api.repositories.TagRepository}.
     *
     * @param tagId ID of the {@link Tag} to delete.
     * @return Tag that was Deleted.
     * @throws TagNotFoundException Thrown if {@link Tag} cannot be found.
     */
    Tag deleteTag(final String tagId) throws TagNotFoundException;

    /**
     * Update a {@link Tag} on the Database.
     *
     * @param tagId The ID of the {@link Tag} to update.
     * @param input An {@link Tag} container that holds
     *              new values for tagId to update to.
     * @return The {@link Tag} that was updated.
     * @throws TagNotFoundException Thrown when the {@link Tag} cannot
     *                              be found.
     */
    Tag updateTag(final String tagId, final Tag input)
            throws TagNotFoundException;

    /**
     * Return if a {@link Tag} exists in the DB or not.
     *
     * @param tag Does this {@link Tag} exist?
     * @return True if the {@link Tag} exists,
     * false if otherwise.
     */
    boolean doesTagExist(final Tag tag);

    /**
     * Return if a {@link  Tag} exists in
     * the DB or not.
     *
     * @param tagId Does the ID for this
     *              {@link Tag} exist?
     * @return True if the {@link Tag} exists,
     * false if otherwise.
     */
    boolean doesTagExist(final String tagId);

    /**
     * Return an {@link Tag} based on its ID.
     *
     * @param tagId The ID of the {@link Tag} to return.
     * @return A {@link Tag} with the
     * ID passed.
     * @throws TagNotFoundException Thrown if the {@link Tag} cannot be found.
     */
    Tag getTag(final String tagId) throws TagNotFoundException;

    /**
     * Return all {@link Tag}s in the DB.
     *
     * @return All {@link Tag}s in the DB.
     */
    Tag[] getAllTags();

}
