package com.sodabottle.freadr.services;

import java.util.List;

import com.sodabottle.freadr.models.BookEntity;
import com.sodabottle.freadr.request.UserBookRequest;

public interface UserBookService {

	boolean addUserBooks(UserBookRequest userBookRequest);

	List<BookEntity> getUserBooks(Long userId);

}
