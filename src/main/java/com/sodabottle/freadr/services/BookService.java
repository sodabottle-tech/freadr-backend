package com.sodabottle.freadr.services;

import com.sodabottle.freadr.models.Book;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BookService {
    Book getById(@Param("id") Integer id);

    Set<Book> getByIds(@Param("ids") Set<Integer> ids);

    Book create(Book event);

    Book update(Book event);

    boolean deleteById(@Param("id") Integer id);

    boolean deleteByIds(@Param("ids") Set<Integer> ids);
}
