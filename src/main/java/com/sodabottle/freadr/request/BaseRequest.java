package com.sodabottle.freadr.request;

import lombok.Data;

@Data
public class BaseRequest {
    private String authToken;
    private Long userId;
}
