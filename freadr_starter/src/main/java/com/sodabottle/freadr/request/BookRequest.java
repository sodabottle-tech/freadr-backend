package com.sodabottle.freadr.request;

import com.sodabottle.freadr.enums.BookStatus;

import lombok.Data;

@Data
public class BookRequest {
	
	private Long bookId;
	
	private BookStatus status;
	
	private Boolean active;
}
