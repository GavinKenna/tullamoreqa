/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answers")
@Transactional
public class Answer extends Entry {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value = org.hibernate.annotations.CascadeType.REFRESH)
    private Question question;

    private boolean chosenAnswer;
    private int upvotes;
    private int downvotes;

    private Answer() {
        super();
    }

    public Answer(Question question, User user, String body) {
        super(user,body);
        this.question = question;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(boolean chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }


    @Override
    public String toString() {
        return "Answer{" +
                "id=" + this.getId() +
                ", question=" + question +
                ", chosenAnswer=" + chosenAnswer +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", user=" + user +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getId() == answer.getId() &&
                isChosenAnswer() == answer.isChosenAnswer() &&
                getUpvotes() == answer.getUpvotes() &&
                getDownvotes() == answer.getDownvotes() &&
                Objects.equals(getQuestion(), answer.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuestion(), isChosenAnswer(), getUpvotes(), getDownvotes());
    }
}
