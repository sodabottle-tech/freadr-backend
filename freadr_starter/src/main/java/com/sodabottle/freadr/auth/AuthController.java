package com.sodabottle.freadr.auth;

import com.sodabottle.freadr.utils.HeaderUtils;
import com.sodabottle.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestHeader HttpHeaders httpHeaders, @RequestHeader("X-User-Id") String userIdStr) {
        String userIdFromToken = authService.validateExpiredAndGetUserId(HeaderUtils.getToken(httpHeaders));

        if (!StringUtils.isEmpty(userIdFromToken) && userIdFromToken.trim().equalsIgnoreCase(userIdStr.trim())) {
            String refreshToken = authService.createToken(userIdStr);
            LogUtil.logMessage("Issued Refresh token for userId %s", new String[]{userIdStr}, log);
            return new ResponseEntity(Collections.singletonMap("refreshToken", refreshToken), HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Unauthorized Operation", HttpStatus.UNAUTHORIZED);
        }
    }
}
