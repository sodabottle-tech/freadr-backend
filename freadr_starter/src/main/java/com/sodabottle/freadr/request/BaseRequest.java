package com.sodabottle.freadr.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest implements Serializable {
    private String authToken;
    private Long userId;
}
