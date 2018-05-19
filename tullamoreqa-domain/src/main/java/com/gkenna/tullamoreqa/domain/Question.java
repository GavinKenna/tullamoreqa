package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User askedBy;

    @ManyToMany
    private List<User> modifiedBy;

    @ManyToOne
    private List<Answer> answers;
    private int upvotes;
    private int downvotes;
    private int score;

    @ManyToOne
    private List<Comment> comments;

    @ManyToMany
    private List<Tag> tags;

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String questionBody;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdatedAt;
}
