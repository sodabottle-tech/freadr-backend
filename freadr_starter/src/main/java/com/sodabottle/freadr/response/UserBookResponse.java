package com.sodabottle.freadr.response;

import java.util.List;

import com.sodabottle.freadr.models.BookEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBookResponse extends BaseResponse {
	
	private List<BookEntity> books;

	public UserBookResponse(List<BookEntity> books) {
		this.books = books;
	}
	
}
