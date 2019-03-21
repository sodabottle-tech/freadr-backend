package com.sodabottle.freadr.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {
    private String code;
    private String message;

    public BaseResponse(String message) {
        this.code = HttpStatus.OK.toString();
        this.message = message;
    }
}
