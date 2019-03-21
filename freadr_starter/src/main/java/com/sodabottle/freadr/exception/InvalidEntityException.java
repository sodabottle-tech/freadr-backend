package com.sodabottle.freadr.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidEntityException extends Exception {
    private String code;
    private String message;
}
