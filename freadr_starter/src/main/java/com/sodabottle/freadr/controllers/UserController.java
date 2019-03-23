package com.sodabottle.freadr.controllers;

import com.sodabottle.freadr.auth.AuthService;
import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.exception.UnAuthorizedException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.request.UserRegistrationRequest;
import com.sodabottle.freadr.response.RegistrationResponse;
import com.sodabottle.freadr.response.UserResponse;
import com.sodabottle.freadr.services.UserService;
import com.sodabottle.freadr.utils.AppUrls;
import com.sodabottle.freadr.utils.GenericResponseUtils;
import com.sodabottle.freadr.validators.GenericRequestValidator;
import com.sodabottle.utils.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class UserController {
    private UserService userService;
    private AuthService authService;

    @Autowired
    public UserController(final UserService userService, final AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // NOTE : Authentication Skipped
    @PostMapping("/reg/user")
    public ResponseEntity createUserProfile(@Valid @RequestBody final UserRegistrationRequest userRegistrationRequest)
            throws InvalidEntityException {
        UserEntity userEntity = userService.createUser(userRegistrationRequest);
        String token = authService.createToken(String.valueOf(userEntity.getId()));

        UserResponse userResponse = ModelMapperUtils.map(userEntity, UserResponse.class).get();
        RegistrationResponse registrationResponse = RegistrationResponse.builder().token(token).user(userResponse).build();
        return GenericResponseUtils.getStandardResponse(registrationResponse, HttpStatus.CREATED);
    }

    @GetMapping(AppUrls.USER + "/{userId}")
    public ResponseEntity getUserProfile(@PathVariable("userId") Long userIdRP, @RequestHeader HttpHeaders httpHeaders)
            throws UnAuthorizedException {
        GenericRequestValidator.validateAuthorization(userIdRP, httpHeaders);

        UserEntity userEntity = userService.getUser(Long.valueOf(userIdRP));
        UserResponse userResponse = ModelMapperUtils.map(userEntity, UserResponse.class).get();
        return GenericResponseUtils.getStandardResponse(userResponse, HttpStatus.OK);
    }

    @PutMapping
    @PatchMapping(AppUrls.USER + "/{userId}")
    public ResponseEntity updateUserProfile(@NotNull @PathVariable("userId") Long userIdRP, @RequestHeader HttpHeaders httpHeaders) throws UnAuthorizedException {
        GenericRequestValidator.validateAuthorization(userIdRP, httpHeaders);
        return GenericResponseUtils.getStandardResponse(null, HttpStatus.OK);
    }

}
