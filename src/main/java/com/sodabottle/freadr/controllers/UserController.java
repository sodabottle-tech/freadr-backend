package com.sodabottle.freadr.controllers;

import com.sodabottle.freadr.services.BookService;
import com.sodabottle.freadr.utils.AppUrls;
import com.sodabottle.freadr.utils.GenericResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @Autowired
    private BookService bookService;

    @GetMapping(AppUrls.USER)
    public ResponseEntity getUserProfile() {
        return GenericResponseUtils.getStandardResponse(null, HttpStatus.OK);
    }

    @PostMapping(AppUrls.USER)
    public ResponseEntity createUserProfile() {
        return GenericResponseUtils.getStandardResponse(null, HttpStatus.OK);
    }

    @PutMapping(AppUrls.USER)
    @PatchMapping(AppUrls.USER)
    public ResponseEntity updateUserProfile(@NotNull @PathVariable("id") Integer id) {
        return GenericResponseUtils.getStandardResponse(null, HttpStatus.OK);
    }
}
