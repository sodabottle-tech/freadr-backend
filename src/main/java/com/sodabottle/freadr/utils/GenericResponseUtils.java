package com.sodabottle.freadr.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponseUtils {
    public static <T> ResponseEntity getStandardResponse(T t, HttpStatus status) {
        if (null == t) {
            return new ResponseEntity("Processing Issue", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(t, status);
    }
}
