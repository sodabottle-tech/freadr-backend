package com.sodabottle.freadr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sodabottle.freadr.models.BookEntity;

@RepositoryRestResource(exported = false)
public interface BookRepo extends JpaRepository<BookEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT b.* FROM book b "
			+ "INNER JOIN user_book ub ON b.id = ub.book_id WHERE ub.user_id = :userId")
	List<BookEntity> findByUserId(@Param("userId") Long userId);
}
