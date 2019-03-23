package com.sodabottle.freadr.utils;

import com.sodabottle.freadr.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HeaderUtils {
    private static JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        HeaderUtils.jwtTokenProvider = jwtTokenProvider;
    }

    public static Long getUserId(HttpHeaders headers) {
        String userIdStr = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(headers.getFirst("Authorization")));
        return Long.valueOf(userIdStr);
    }

    public static String getToken(HttpHeaders headers) {
        return jwtTokenProvider.resolveToken(headers.getFirst("Authorization"));
    }
}
