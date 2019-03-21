package com.sodabottle.freadr.validators;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.BookEntity;
import com.sodabottle.freadr.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookValidator implements EntityValidator<BookEntity> {

    private BookService bookService;

    @Autowired
    public BookValidator(final BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookEntity validate(Long id) throws InvalidEntityException {
        BookEntity book = bookService.getById(id);
        if (null == book) {
            //throw InvalidEntityException.builder().code("INV_BOOK").message("Invalid BookEntity ID").build();
        }
        return book;
    }
}
