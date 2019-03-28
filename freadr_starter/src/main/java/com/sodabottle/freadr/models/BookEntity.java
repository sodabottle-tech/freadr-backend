package com.sodabottle.freadr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity(name = "book")
@Data
@Audited
public class BookEntity implements Serializable {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "{name.notempty}")
    private String name;

    @NotEmpty(message = "{desc.notempty}")
    private String description;

    private String popularQuotes;

    private boolean deleted;

    private String thumbnailUrl;

    private String author;

    private String genre;
    
    @OneToMany(mappedBy="book", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserBookEntity> useBooks = new ArrayList<UserBookEntity>();
}
