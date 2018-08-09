/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.controllers;

import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.http.ResponseEntity;

public interface TagController {
    /**
     * POST Method
     *
     * @param input
     * @return
     */
    ResponseEntity<?> addTag(Tag input);

    /**
     * GET Method
     *
     * @param tag
     * @return
     */
    ResponseEntity<Tag> getTag(String tag);

    /**
     * PUT Method
     *
     * @param tag
     * @param input
     * @return
     */
    ResponseEntity<?> updateTag(String tag, Tag input);


    /**
     * DELETE Method
     *
     * @param tag
     * @return
     */
    ResponseEntity<?> deleteTag(String tag);
}
