/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
     * List of Privileges that a Role can perform, i.e. Delete
     * an Entity, or create an Entity, etc.
     */
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    public <T extends Domain> void patch(T entity) {

    }
}
