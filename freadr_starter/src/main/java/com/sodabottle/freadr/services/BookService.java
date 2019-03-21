package com.sodabottle.freadr.services;

import com.sodabottle.freadr.models.BookEntity;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BookService {
    BookEntity getById(@Param("id") Long id);

    Set<BookEntity> getByIds(@Param("ids") Set<Long> ids);

    BookEntity create(BookEntity event);

    BookEntity update(BookEntity event);

    boolean deleteById(@Param("id") Long id);

    boolean deleteByIds(@Param("ids") Set<Long> ids);
}
