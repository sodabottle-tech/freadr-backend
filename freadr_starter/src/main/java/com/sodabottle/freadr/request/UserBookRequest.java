package com.sodabottle.freadr.request;

import java.util.List;

import lombok.Data;

@Data
public class UserBookRequest {
	
	private Long userId;
	private List<BookRequest> books;
}
