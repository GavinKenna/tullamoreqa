package com.gkenna.tullamoreqa.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    //@GeneratedValue(strategy = GenerationType.)
    private String name;

    @NotBlank
    private String description;

    public Tag(String name) {
        this.description = "Desc";
        this.name = name;
    }
}
