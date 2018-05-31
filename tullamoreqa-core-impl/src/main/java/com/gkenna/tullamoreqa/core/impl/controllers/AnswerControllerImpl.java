/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl.controllers;

import com.gkenna.tullamoreqa.core.api.controllers.AnswerController;
import com.gkenna.tullamoreqa.core.api.services.AnswerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerControllerImpl implements AnswerController {

    private static final Logger LOGGER = LogManager.getLogger(AnswerControllerImpl.class);

    @Autowired
    private AnswerService answerService;
}
