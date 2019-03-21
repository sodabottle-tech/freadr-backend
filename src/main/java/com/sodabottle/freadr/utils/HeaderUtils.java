package com.sodabottle.freadr.utils;

import org.springframework.http.HttpHeaders;

public class HeaderUtils {

    public static Long getUserId(HttpHeaders headers) {
        return Long.valueOf(headers.getFirst("X-User-Id"));
    }
}
