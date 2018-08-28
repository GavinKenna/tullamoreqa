/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    /**
     * List of Roles that encapsulate the User.
     * i.e. Admin, Moderator, RegularUser, etc.
     * TODO Perhaps make this bi-directional.
     */
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Role> roles;

    /**
     * The email address of the User.
     */
    @NotBlank
    @NotNull
    private String email;

    /**
     * First name of the User.
     */
    @NotBlank
    @NotNull
    private String firstName;

    /**
     * Second name of the User.
     */
    @NotBlank
    @NotNull
    private String secondName;

    /**
     * Hashed password of the User.
     */
    @NotBlank
    @NotNull
    private String password;

    /**
     * URL pointing towards the image location for
     * the Users profile picture.
     */
    @NotBlank
    @NotNull
    private String avatarURL;

    /**
     * Bio or description that the User will write about
     * themselves.
     */
    @NotBlank
    @NotNull
    private String description;

    /**
     * If the User is enabled or not. If a  {@link User} isn't enabled yet
     * then they cannot create{@link Question}s or {@link Comment}s yet,
     * or submit {@link Answer}s.
     */
    @NotBlank
    private boolean isEnabled;

    /**
     * Create a new User.
     *
     * @param username The unique ID of the User.
     * @since 0.0.0
     */
    public User(final String username) {
        this.username = username;
    }

    /**
     * Default Constructor. Shouldn't be called.
     * See Effective Java (2nd+3rd Edition).
     *
     * @since 0.0.0
     */
    private User() {

    }

    @Override
    public final <T extends Domain> void patch(final T entity) {
        final User input = (User) entity;
    }

    /**
     * Return the Username.
     *
     * @return Username of the User.
     * @since 0.0.0
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Set the Username.
     *
     * @param username Username of the User.
     * @since 0.0.0
     */
    public final void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Return list of {@link Role}s that the {@link User} is part of.
     *
     * @return List of Roles
     * @since 0.0.11
     */
    public final Set<Role> getRoles() {
        return roles;
    }

    /**
     * Set list of {@link Role}s that the {@link User} is part of.
     *
     * @param roles Set of Roles that the User is part of.
     * @since 0.0.11
     */
    public final void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Return the {@link User}s email address.
     *
     * @return {@link User}s email address.
     * @since 0.0.11
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Set the {@link User}s email address.
     *
     * @param email Email address of the User.
     * @since 0.0.11
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Return the {@link User}s first name.
     *
     * @return {@link User}s first name.
     * @since 0.0.11
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Set the {@link User}s first name.
     *
     * @param firstName First name of the User.
     * @since 0.0.11
     */
    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return the {@link User}s last name.
     *
     * @return {@link User}s last name.
     * @since 0.0.11
     */
    public final String getSecondName() {
        return secondName;
    }

    /**
     * Set the {@link User}s last name.
     *
     * @param secondName Users second name.
     * @since 0.0.11
     */
    public final void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    /**
     * Return the {@link User}s password in an Encrypted/Hashed format.
     *
     * @return {@link User}s hashed password.
     * @since 0.0.11
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Set the {@link User}s password. It will be hashed in this method.
     *
     * @param password Password of the User.
     * @since 0.0.11
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Return the URL pointing towards the {@link User}s Avatar
     * (profile picture).
     *
     * @return URL of image.
     * @since 0.0.11
     */
    public final String getAvatarURL() {
        return avatarURL;
    }

    /**
     * Set the URL pointing towards the {@link User}s Avatar
     * (profile picture).
     *
     * @param avatarURL URL of the image location.
     * @since 0.0.11
     */
    public final void setAvatarURL(final String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /**
     * Return the description / bio of the {@link User}.
     *
     * @return Bio of the {@link User}.
     * @since 0.0.11
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set the description / bio of the {@link User}.
     *
     * @param description Bio of the User.
     * @since 0.0.11
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Return if the {@link User} is enabled on the system yet. If a
     * {@link User} isn't enabled yet then they cannot create {@link Question}s
     * or {@link Comment}s yet, or submit {@link Answer}s.
     *
     * @return true if the {@link User} is enabled.
     * @since 0.0.11
     */
    public final boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Set if the {@link User} is enabled on the system yet. If a
     * {@link User} isn't enabled yet then they cannot create {@link Question}s
     * or {@link Comment}s yet, or submit {@link Answer}s.
     *
     * @param enabled If the User is enabled or not.
     * @since 0.0.11
     */
    public final void setEnabled(final boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return isEnabled() == user.isEnabled()
                && Objects.equals(getUsername(), user.getUsername())
                && Objects.equals(getRoles(), user.getRoles())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getFirstName(), user.getFirstName())
                && Objects.equals(getSecondName(), user.getSecondName())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getAvatarURL(), user.getAvatarURL())
                && Objects.equals(getDescription(), user.getDescription());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getUsername(), getRoles(), getEmail(),
                getFirstName(), getSecondName(), getPassword(),
                getAvatarURL(), getDescription(), isEnabled());
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", avatarURL='").append(avatarURL).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isEnabled=").append(isEnabled);
        sb.append('}');
        return sb.toString();
    }
}
