package com.sodabottle.freadr.controllers;

import com.sodabottle.freadr.auth.AuthService;
import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.exception.UnAuthorizedException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.request.UserLocationRequest;
import com.sodabottle.freadr.request.UserRequest;
import com.sodabottle.freadr.response.RegistrationResponse;
import com.sodabottle.freadr.response.UserLocationResponse;
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
    @PostMapping(AppUrls.USER_REG)
    public ResponseEntity createUserProfile(@Valid @RequestBody final UserRequest userRequest)
            throws InvalidEntityException {
        UserEntity userEntity = userService.createUser(userRequest);
        String token = authService.createToken(String.valueOf(userEntity.getId()));

        UserResponse userResponse = ModelMapperUtils.map(userEntity, UserResponse.class).get();
        RegistrationResponse registrationResponse = RegistrationResponse.builder().token(token).user(userResponse).build();
        return GenericResponseUtils.getStandardResponse(registrationResponse, HttpStatus.CREATED);
    }

    @GetMapping(AppUrls.UPDATE_USER)
    public ResponseEntity getUserProfile(@PathVariable("userId") Long userIdRP, @RequestHeader HttpHeaders httpHeaders)
            throws UnAuthorizedException {
        GenericRequestValidator.validateAuthorization(userIdRP, httpHeaders);

        UserEntity userEntity = userService.getUser(Long.valueOf(userIdRP));
        UserResponse userResponse = ModelMapperUtils.map(userEntity, UserResponse.class).get();
        return GenericResponseUtils.getStandardResponse(userResponse, HttpStatus.OK);
    }

    @PutMapping(AppUrls.UPDATE_USER)
    @PatchMapping(AppUrls.UPDATE_USER)
    public ResponseEntity updateUserProfile(@NotNull @PathVariable("userId") Long userIdRP, @RequestHeader HttpHeaders httpHeaders,
                                            @Valid @RequestBody final UserRequest userRequest)
            throws UnAuthorizedException, InvalidEntityException {
        GenericRequestValidator.validateAuthorization(userIdRP, httpHeaders);

        userRequest.setUserId(userIdRP);
        UserEntity userEntity = userService.updateUser(userRequest);
        UserResponse userResponse = ModelMapperUtils.map(userEntity, UserResponse.class).get();
        return GenericResponseUtils.getStandardResponse(userResponse, HttpStatus.OK);
    }
    
    @PostMapping(AppUrls.UPDATE_USER_LOCATION)
    public ResponseEntity updateUserLocation(@NotNull @PathVariable("userId") Long userIdRP, @RequestHeader HttpHeaders httpHeaders,
                                            @Valid @RequestBody final UserLocationRequest locationRequest)
            throws UnAuthorizedException, InvalidEntityException {
        GenericRequestValidator.validateAuthorization(userIdRP, httpHeaders);

        locationRequest.setUserId(userIdRP);
        UserEntity userEntity = userService.updateUserLocation(locationRequest);
        UserLocationResponse userLocationResponse = ModelMapperUtils.map(userEntity, UserLocationResponse.class).get();
        return GenericResponseUtils.getStandardResponse(userLocationResponse, HttpStatus.OK);
    }
}
