/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.CommentController;
import com.gkenna.tullamoreqa.core.api.services.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentControllerImpl implements CommentController {

    private static final Logger LOGGER = LogManager.getLogger(CommentControllerImpl.class);

    @Autowired
    private CommentService commentService;


}
