package com.sodabottle.freadr.services;

import javax.validation.Valid;

import com.sodabottle.freadr.exception.InvalidEntityException;
import com.sodabottle.freadr.models.UserEntity;
import com.sodabottle.freadr.request.UserLocationRequest;
import com.sodabottle.freadr.request.UserRequest;

public interface UserService {
    UserEntity createUser(final UserRequest userRequest) throws InvalidEntityException;

    UserEntity getUser(final Long userId);

    UserEntity updateUser(final UserRequest userRequest) throws InvalidEntityException;

	UserEntity updateUserLocation(@Valid UserLocationRequest locationRequest) throws InvalidEntityException;
}
