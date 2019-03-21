package com.sodabottle.freadr.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvalidEntityException extends Exception {
    private String code;
    private String message;
}
