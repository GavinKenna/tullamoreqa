/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

/**
 * A representative of a User.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Entity
@Table(name = "users")
public class User implements Domain {

    /**
     * The username of the User, seen as the ID.
     */
    @Id
    @NotBlank
    @NotNull
    private String username;

    private Set<Role> roles;

    private String firstName;
    private String secondName;
    private String password;
    private String avatarURL;
    private String description;
    private boolean isEnabled;


    /**
     * Create a new User.
     *
     * @param username The unique ID of the User.
     */
    public User(final String username) {
        this.username = username;
    }

    /**
     * Default Constructor. Shouldn't be called.
     * See Effective Java (2nd+3rd Edition).
     */
    private User() {

    }

    /**
     * Return the Username.
     *
     * @return Username of the User.
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Set the Username.
     *
     * @param username Username of the User.
     */
    public final void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public final <T extends Domain> void patch(final T entity) {
        final User input = (User) entity;


    }
}
