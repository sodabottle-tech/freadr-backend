package com.sodabottle.freadr.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sodabottle.freadr.models.BookEntity;
import com.sodabottle.freadr.request.UserBookRequest;
import com.sodabottle.freadr.response.BaseResponse;
import com.sodabottle.freadr.response.UserBookResponse;
import com.sodabottle.freadr.services.UserBookService;
import com.sodabottle.freadr.utils.AppUrls;
import com.sodabottle.freadr.utils.GenericResponseUtils;
import com.sodabottle.freadr.utils.ResponseMessages;

@RestController
public class UserBookController {
	
	@Autowired
	UserBookService userBookService;
	
	@GetMapping(AppUrls.USERS_ID_BOOKS)
    public ResponseEntity getUsersBooks(@PathVariable("user-id") Long userId) {
		
		List<BookEntity> books = userBookService.getUserBooks(userId);
		
        return GenericResponseUtils.getStandardResponse(new UserBookResponse(books), HttpStatus.OK);
    }
	
	@PostMapping(AppUrls.USERS_ID_BOOKS)
    public ResponseEntity addUsersBooks(@Valid @RequestBody UserBookRequest userBookRequest) {
		
		if(! userBookService.addUserBooks(userBookRequest)) {
			return GenericResponseUtils.getStandardResponse(new BaseResponse(ResponseMessages.USER_BOOK_FAILED_TO_UPDATE), HttpStatus.BAD_REQUEST);
		}
			
        return GenericResponseUtils.getStandardResponse(new BaseResponse(ResponseMessages.USER_BOOK_UPDATED_SUCCESSFULY), HttpStatus.OK);
    }
}
