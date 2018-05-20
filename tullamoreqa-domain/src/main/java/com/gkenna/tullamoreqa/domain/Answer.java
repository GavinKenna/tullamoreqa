package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
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

    @OneToMany
    private Set<Comment> comments;

    private boolean chosenAnswer;
    private int upvotes;
    private int downvotes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
