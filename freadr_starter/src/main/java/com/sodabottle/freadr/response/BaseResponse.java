package com.sodabottle.freadr.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class BaseResponse {
    private String code;
    private String message;

    public BaseResponse(String message) {
        this.code = HttpStatus.OK.toString();
        this.message = message;
    }
}
