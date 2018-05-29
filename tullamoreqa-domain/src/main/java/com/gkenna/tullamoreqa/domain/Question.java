package com.gkenna.tullamoreqa.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "questions")
public class Question extends Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "mod_user_username",nullable = true)
    private User modifiedBy = null;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdatedAt;

    @NotBlank
    private String title;

    private int upvotes;
    private int downvotes;
    private int score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return getId() == question.getId() &&
                getUpvotes() == question.getUpvotes() &&
                getDownvotes() == question.getDownvotes() &&
                getScore() == question.getScore() &&
                Objects.equals(getModifiedBy(), question.getModifiedBy()) &&
                Objects.equals(getTags(), question.getTags()) &&
                Objects.equals(getCreatedAt(), question.getCreatedAt()) &&
                Objects.equals(getLastUpdatedAt(), question.getLastUpdatedAt()) &&
                Objects.equals(getTitle(), question.getTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getModifiedBy(), getTags(), getCreatedAt(), getLastUpdatedAt(), getTitle(), getUpvotes(), getDownvotes(), getScore());
    }

    public Question() {
        //this.answers = new HashSet<Answer>();
        //this.comments = new HashSet<Comment>();
        this.tags = new HashSet<Tag>();
        this.createdAt = new Date();
        this.lastUpdatedAt = new Date();
        this.upvotes = 0;
        this.downvotes = 0;
}

    public String getTitle() {
        return title;
    }

    public void setTitle(String questionTitle) {
        this.title = questionTitle;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

   /* public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }*/

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String questionBody) {
        this.body = questionBody;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", modifiedBy=" + modifiedBy +
                ", tags=" + tags +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", title='" + title + '\'' +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", score=" + score +
                ", user=" + user +
                ", body='" + body + '\'' +
                '}';
    }

    /*public long getId() {
        return id;
    }*/

   /* public void addAnswer(Answer answer){
        this.answers.add(answer);
        answer.setQuestion(this);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        //comment.setParent(this);
    }*/
}
