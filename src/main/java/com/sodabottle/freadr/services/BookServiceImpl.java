package com.sodabottle.freadr.services;

import com.sodabottle.freadr.models.Book;
import com.sodabottle.freadr.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public Book getById(Integer id) {
        return bookRepo.getOne(id);
    }

    @Override
    public Set<Book> getByIds(Set<Integer> ids) {
        return null;
    }

    @Override
    public Book create(Book event) {
        return bookRepo.saveAndFlush(event);
    }

    @Override
    public Book update(Book event) {
        return bookRepo.save(event);
    }

    @Override
    public boolean deleteById(Integer id) {
        bookRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByIds(Set<Integer> ids) {
        //bookRepo.de;
        return false;
    }
}
