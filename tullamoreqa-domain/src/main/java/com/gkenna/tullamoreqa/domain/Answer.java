package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity=Question.class)
    @JoinColumn(name="question_id")
    private Question question;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Comment> comments;

    @NotBlank
    private String body;

    private boolean chosenAnswer;
    private int upvotes;
    private int downvotes;

    public Answer() {
        this.body = "Body";
        this.question = new Question();
        this.comments = new HashSet<>();
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
