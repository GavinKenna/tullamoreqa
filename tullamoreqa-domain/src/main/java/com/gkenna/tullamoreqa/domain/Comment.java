package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="user_id")
    private User user;

    @NotBlank
    private String body;

    private int upvotes;
    private int downvotes;

    public Comment() {
        this.body = "Body";
        this.user = new User();
    }
}
