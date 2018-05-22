package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer extends Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Comment> comments;

    private boolean chosenAnswer;
    private int upvotes;
    private int downvotes;

    public Answer() {
        this.comments = new HashSet<>();
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
        //this.question.getAnswers().add(this);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

}
