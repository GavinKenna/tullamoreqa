/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.TagController;
import com.gkenna.tullamoreqa.core.api.services.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagControllerImpl implements TagController {

    private static final Logger LOGGER = LogManager.getLogger(TagControllerImpl.class);

    @Autowired
    private TagService tagService;
}
