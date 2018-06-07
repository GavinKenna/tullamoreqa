/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AnswerTest {

    @InjectMocks
    private final Answer validAnswer;

    @InjectMocks
    private final Answer invalidAnswer;

    private final String validBody = "Answer Body";

    @Mock
    private Question mockedQuestion;

    @Mock
    private User mockedUser;
    
    public AnswerTest(){
        MockitoAnnotations.initMocks(this);
        validAnswer = new Answer(mockedQuestion, mockedUser, validBody);
        invalidAnswer = new Answer(mockedQuestion, mockedUser, validBody);
    }

    @Test
    public void getQuestionValid() {
        assert validAnswer.getQuestion().equals(mockedQuestion);
    }

    @Test
    public void setQuestion() {
        /*when(mockCustomObject.testMethod()).thenReturn(...);
        mainClass.makeCall();
        verify(mockCustomObject).testMethod();*/
    }

    @Test
    public void getBody() {
    }

    @Test
    public void setBody() {
    }

    @Test
    public void isChosenAnswer() {
    }

    @Test
    public void setChosenAnswer() {
    }

    @Test
    public void getUpvotes() {
    }

    @Test
    public void setUpvotes() {
    }

    @Test
    public void getDownvotes() {
    }

    @Test
    public void setDownvotes() {
    }
}