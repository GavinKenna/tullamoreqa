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
    private final String validBodyTwo = "Another Answer Body";

    @Mock
    private Question mockedQuestion;

    @Mock
    private Question mockedQuestionTwo;

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
        validAnswer.setQuestion(mockedQuestionTwo);
        assert validAnswer.getQuestion().equals(mockedQuestionTwo);
        assert validAnswer.getQuestion().equals(mockedQuestion) == false;
        validAnswer.setQuestion(mockedQuestion);
        assert validAnswer.getQuestion().equals(mockedQuestion);
        assert validAnswer.getQuestion().equals(mockedQuestionTwo) == false;
        /*when(mockCustomObject.testMethod()).thenReturn(...);
        mainClass.makeCall();
        verify(mockCustomObject).testMethod();*/
    }

    @Test
    public void getBody() {
        assert validAnswer.getBody().equals(this.validBody);
    }

    @Test
    public void setBody() {
        validAnswer.setBody(validBodyTwo);
        assert validAnswer.getBody().equals(this.validBodyTwo);
        assert validAnswer.getBody().equals(this.validBody) == false;
        validAnswer.setBody(validBody);
    }

    @Test
    public void isChosenAnswer() {
        assert validAnswer.isChosenAnswer() == false;
    }

    @Test
    public void setChosenAnswer() {
        validAnswer.setChosenAnswer(true);
        assert validAnswer.isChosenAnswer() == true;
        validAnswer.setChosenAnswer(false);
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