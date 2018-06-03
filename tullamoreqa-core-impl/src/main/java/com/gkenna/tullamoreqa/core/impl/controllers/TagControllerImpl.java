/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.TagController;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import com.gkenna.tullamoreqa.domain.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagControllerImpl implements TagController {

    private static final Logger LOGGER = LogManager.getLogger(TagControllerImpl.class);

    @Autowired
    private TagService tagService;

    @Override
    public ResponseEntity<?> addTag(Tag input) {
        return null;
    }

    @Override
    public Tag getTag(String tag) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateTag(String tag, Tag input) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTag(String tag) {
        return null;
    }
}
