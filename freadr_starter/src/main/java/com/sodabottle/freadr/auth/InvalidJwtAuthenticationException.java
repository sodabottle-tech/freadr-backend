package com.sodabottle.freadr.auth;

public class InvalidJwtAuthenticationException extends Exception {
    private String message;

    public InvalidJwtAuthenticationException(String message) {
        this.message = message;
    }
}
