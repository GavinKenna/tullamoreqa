/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An Authority entity, which describes what a User can and can't do.
 *
 * @author Gavin Kenna
 * @since 0.0.11
 */
@Entity
@Table(name = "authorities")
public class Authority implements Domain {
    public <T extends Domain> void patch(T entity) {

    }
}
