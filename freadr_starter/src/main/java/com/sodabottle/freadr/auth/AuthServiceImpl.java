package com.sodabottle.freadr.auth;

import com.sodabottle.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(final AuthenticationManager authenticationManager, final JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String createToken(final String userId) {
        LogUtil.logMessage("Creating Token for UserId %s ", new String[]{userId}, log);
        return jwtTokenProvider.createToken(userId, null);
    }

//    @Override
//    public void validateToken(final String userId) {
//        try {
//            LogUtil.logMessage("Authenticating Token for UserId %s ", new String[]{userId}, log);
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, "testpass0987123"));
//        } catch (AuthenticationException e) {
//            LogUtil.logMessage("BadCredentialsException Token for UserId %s ", new String[]{userId}, log);
//            throw new BadCredentialsException("Invalid username/token supplied");
//        }
//    }

    @Override
    public String validateExpiredAndGetUserId(String token) {
        return jwtTokenProvider.validateExpiredAndGetUserId(token);
    }
}
