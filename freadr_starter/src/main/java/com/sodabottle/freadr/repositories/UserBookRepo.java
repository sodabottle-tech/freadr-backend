package com.sodabottle.freadr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sodabottle.freadr.models.BookEntity;
import com.sodabottle.freadr.models.UserBookEntity;

@RepositoryRestResource(exported = false)
public interface UserBookRepo extends JpaRepository<UserBookEntity, Long>{

	@Query(nativeQuery = true, value = "SELECT distinct b FROM book b INNER JOIN user_book ub ON b.id = ub.book_id WHERE ub.user_id = :userId")
	List<BookEntity> findByUserId(@Param("userId")  Long userId);

	@Query(nativeQuery = true, value = "SELECT * FROM user_book WHERE user_id = :userId AND book_id = :bookId")
	UserBookEntity findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

//	@Query(nativeQuery = true, value = "SELECT * FROM user_book WHERE user_id = :userId")
//	List<UserBookEntity> findByUserId(@Param("userId") Long userId);
	
	

}
