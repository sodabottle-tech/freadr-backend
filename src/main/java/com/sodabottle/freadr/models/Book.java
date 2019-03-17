package com.sodabottle.freadr.models;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity(name = "book")
@Data
@Audited
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "{name.notempty}")
    private String name;

    @NotEmpty(message = "{desc.notempty}")
    private String description;

    private String popularQuotes;

    private boolean deleted;

}
