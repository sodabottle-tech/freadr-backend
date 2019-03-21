package com.sodabottle.freadr.services;

import com.sodabottle.freadr.models.BookEntity;
import com.sodabottle.freadr.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public BookEntity getById(Long id) {
        return bookRepo.getOne(id);
    }

    @Override
    public Set<BookEntity> getByIds(Set<Long> ids) {
        return null;
    }

    @Override
    public BookEntity create(BookEntity event) {
        return bookRepo.saveAndFlush(event);
    }

    @Override
    public BookEntity update(BookEntity event) {
        return bookRepo.save(event);
    }

    @Override
    public boolean deleteById(Long id) {
        bookRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        return false;
    }
}
