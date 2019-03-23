package com.sodabottle.logs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
