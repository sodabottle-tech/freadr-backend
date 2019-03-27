package com.sodabottle.freadr.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import com.sodabottle.freadr.enums.BookStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_book")
@Data
@Audited
@NoArgsConstructor
public class UserBookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private BookEntity book;
	
	@ManyToOne
	@JoinColumn
	private UserEntity user;
	
	@Column(name = "status")
    @Enumerated(EnumType.STRING)
	private BookStatus status;
	
	private Boolean active = true;
	
	private Date createdAt = new Date();
	
	private Date modifiedAt;
	
	private String action;

	public UserBookEntity(BookEntity book, UserEntity user, BookStatus status, Boolean active, String action) {
		this.book = book;
		this.user = user;
		this.status = status;
		this.active = active;
		this.action = action;
	}

}
