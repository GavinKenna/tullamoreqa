/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    private String name;

    @NotBlank
    private String description;

    public Tag(String name) {
        this.description = "Desc";
        this.name = name;
    }

    private Tag() {

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return this.getName();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(getName(), tag.getName()) &&
                Objects.equals(getDescription(), tag.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getDescription());
    }
}
