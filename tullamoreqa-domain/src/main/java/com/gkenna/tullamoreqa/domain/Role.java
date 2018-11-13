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
 * A Role entity, which describes what a User can and can't do.
 * Roles can be assigned to multiple Users, and Users can have multiple
 * Roles.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
@Entity
@Table(name = "roles")
public class Role implements Domain {

    /**
     * The name of the Role, seen as the ID.
     */
    @Id
    @NotBlank
    @NotNull
    private String roleName;

    /**
     * List of Privileges that a Role can perform, i.e. Delete
     * an Entity, or create an Entity, etc.
     */
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    /**
     * Create a new Role.
     *
     * @param roleName The unique ID of the Role.
     * @since 0.0.11
     */
    public Role(final String roleName) {
        this.roleName = roleName;
    }

    @Override
    public final <T extends Domain> void patch(final T entity) {
        final Role input = (Role) entity;
        final Set<Authority> inputAuthorities = input.getAuthorities();
        if (inputAuthorities != null) {
            this.setAuthorities(inputAuthorities);
        }
    }

    @Override
    public final <T extends Domain> void update(final T entity) {
        final Role input = (Role) entity;
        final Set<Authority> inputAuthorities = input.getAuthorities();
        this.setAuthorities(inputAuthorities);
    }

    /**
     * Get the list of #{@link Authority}'s that the #{@link Role} contains.
     *
     * @return List of #{@link Authority}'s
     * @since 0.0.11
     */
    public final Set<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * Set the list of #{@link Authority}'s that the #{@link Role} contains.
     *
     * @param authorities List of #{@link Authority}'s
     * @since 0.0.11
     */
    public final void setAuthorities(final Set<Authority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Add an Authority to this Role.
     *
     * @param authority Authority to add.
     * @since 0.0.11
     */
    public final void addAuthority(final Authority authority) {
        this.authorities.add(authority);
    }

    /**
     * Remove an Authority from this Role.
     *
     * @param authority Authority to remove.
     * @since 0.0.11
     */
    public final void removeAuthority(final Authority authority) {
        this.authorities.remove(authority);
    }

    /**
     * Retrieve the name of the Role.
     *
     * @return Name of the Role.
     * @since 0.0.11
     */
    public final String getRoleName() {
        return this.roleName;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(getRoleName(), role.getRoleName())
                && Objects.equals(getAuthorities(), role.getAuthorities());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getRoleName(), getAuthorities());
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("roleName='").append(roleName).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append('}');
        return sb.toString();
    }
}
