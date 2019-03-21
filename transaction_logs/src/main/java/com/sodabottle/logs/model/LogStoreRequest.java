package com.sodabottle.logs.model;

import lombok.Data;

import java.util.Date;

@Data
public class LogStoreRequest {
    private String userId;
    private String token;
    private String activity;
    private String httpVerb;
    private String url;
    private String requestJson;
    private String responseJson;
    private Date requestTS;
}
