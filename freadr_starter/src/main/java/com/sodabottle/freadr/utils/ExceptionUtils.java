package com.sodabottle.freadr.utils;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.exception.UnAuthorizedException;

public class ExceptionUtils {
    public static final String MM_PARSE_ERROR = "MM_PARSE_ERROR";
    public static final String DUPLICATE_USER = "DUPLICATE_USER";
    public static final String UNAUTHORIZED_ACCESS = "UNAUTHORIZED_ACCESS";
    public static final String INVALID_ENTITY = "INVALID_ENTITY";

    public static void getInvalidEntityException(String code, String message) throws InvalidEntityException {
        throw new InvalidEntityException(code, message);
    }

    public static void getUnAuthorizedException(String code, String message) throws UnAuthorizedException {
        throw new UnAuthorizedException(code, message);
    }
}
