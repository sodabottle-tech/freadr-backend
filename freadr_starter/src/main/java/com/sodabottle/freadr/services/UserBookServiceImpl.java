package com.sodabottle.freadr.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodabottle.freadr.models.BookEntity;
import com.sodabottle.freadr.models.UserBookEntity;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.repositories.BookRepo;
import com.sodabottle.freadr.repositories.UserBookRepo;
import com.sodabottle.freadr.repositories.UserRepo;
import com.sodabottle.freadr.request.UserBookRequest;

@Service
public class UserBookServiceImpl implements UserBookService {

	@Autowired
	UserBookRepo userBookRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public boolean addUserBooks(UserBookRequest userBookRequest) {
		
		List<UserBookEntity> books  = new ArrayList<UserBookEntity>();
		
		Optional<UserEntity> user = userRepo.findById(userBookRequest.getUserId());
		if(!user.isPresent())
			return false;

		userBookRequest.getBooks().stream().forEach( (bookRequest) -> { 
			
			UserBookEntity userBook = userBookRepo.findByUserIdAndBookId(user.get().getId(), bookRequest.getBookId());
			if(null != userBook) {
				
				userBook.setAction("UPDATE ACTION");
				userBook.setStatus(bookRequest.getStatus());
				userBook.setActive(bookRequest.getActive());
				//userBookRepo.save(userBook);
				books.add(userBook);
			}
			else {
				Optional<BookEntity> book = bookRepo.findById(bookRequest.getBookId());
				if(book.isPresent())
					books.add(new UserBookEntity(book.get(), user.get(), bookRequest.getStatus(), true, "CREATE ACTION"));
			}

		});
		//update user books
		userBookRepo.saveAll(books);
		return true;
	}

	@Override
	public List<BookEntity> getUserBooks(Long userId) {
		
		List<BookEntity> books = null;
		try {
			books = bookRepo.findByUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

}
