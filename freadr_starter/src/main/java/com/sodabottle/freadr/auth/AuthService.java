package com.sodabottle.freadr.auth;

public interface AuthService {
    String createToken(String userId);

    String validateExpiredAndGetUserId(String token);
}
